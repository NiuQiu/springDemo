package com.andrew.demo.service;

import com.andrew.demo.DAO.BookDAO;
import com.andrew.demo.model.Author;
import com.andrew.demo.model.Book;
import com.andrew.demo.model.PostBody;
import com.andrew.demo.model.Publisher;
import com.andrew.demo.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BookService {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookDAO bookDAO;

    private static final Logger LOGGER= LoggerFactory.getLogger(BookService.class);

    public String addBook(PostBody payload){

        Book book = payload.getBook();

        if(payload.getAuthor() == null){
            LOGGER.error("Missing author information in the request body");
            throw new IllegalStateException("No author is contained in payload");
        }
        Author author = payload.getAuthor();

        if(payload.getAuthor().getAuthorId() == null){
            author.setAuthorId(Utility.generateUUID());
            LOGGER.debug("Inserting author {} to author table", author.getAuthorId());
            authorService.addAuthor(author);
        }

        if(!authorService.getAuthor(author.getAuthorId())){
            LOGGER.debug("Inserting author {} into author table", author.getAuthorId());
            authorService.addAuthor(author);
        }

        if(payload.getPublisher() == null){
            LOGGER.error("Missing publisher information in the request body");
            throw new IllegalStateException("No publisher is contained in payload");
        }
        Publisher publisher = payload.getPublisher();
        if(publisher.getPublisherId() == null){
            publisher.setPublisherId(Utility.generateUUID());
            LOGGER.debug("Inserting publisher {} to publisher table", publisher.getPublisherId());
            publisherService.addPulbisher(publisher);
        }

        if(!publisherService.getPubliser(publisher.getPublisherId())){
            LOGGER.debug("Inserting publisher {} in {} to publisher table", publisher.getPublisherId(), publisher.getCountry());
            publisherService.addPulbisher(publisher);
        }

        if(book.getBookId() == null){
            book.setBookId(Utility.generateUUID());
        }

        book.setAuthor(author);
        book.setPublisher(publisher);

        bookDAO.save(book);
        return book.getBookId().toString();
    }

    public List<Book> getAllBooks(){
        return (List<Book>)bookDAO.findAll();
    }

    public Book getBook(String bookId){
        if(bookId != null){
            UUID id = UUID.fromString(bookId);
            return bookDAO.findById(id).get();
        }else{
            throw new IllegalArgumentException("book id is not provided");
        }
    }
}
