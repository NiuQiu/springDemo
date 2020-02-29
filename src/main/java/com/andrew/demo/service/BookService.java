package com.andrew.demo.service;

import com.andrew.demo.DAO.BookDAO;
import com.andrew.demo.andrewException.DuplicationException;
import com.andrew.demo.model.Author;
import com.andrew.demo.model.Book;
import com.andrew.demo.model.PostBody;
import com.andrew.demo.model.Publisher;
import com.andrew.demo.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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

    public String addBook(PostBody payload) throws Exception{

        Book book = payload.getBook();

        if(payload.getAuthor() == null){
            LOGGER.error("Missing author information in the request body");
            throw new IllegalStateException("No author is contained in payload");
        }
        Author author = payload.getAuthor();
        UUID authorId = author.getAuthorId();

        if(authorId == null){
            Author current = authorService.findAuthorName(author.getFirstName(), author.getLastName());
            if(current == null){
                author.setAuthorId(Utility.generateUUID());
                LOGGER.debug("Inserting author {} to author table", author.getAuthorId());
                authorService.addAuthor(author);
            }else{
                author = current;
            }
        }else{
            if(!authorService.findAuthor(authorId)){
                LOGGER.debug("Inserting author {} into author table", author.getAuthorId());
                authorService.addAuthor(author);
            }else{
                author = authorService.getAuthor(authorId);
            }
        }

        if(payload.getPublisher() == null){
            LOGGER.error("Missing publisher information in the request body");
            throw new IllegalStateException("No publisher is contained in payload");
        }
        Publisher publisher = payload.getPublisher();
        String publisherName = publisher.getName();
        UUID publisherId = publisher.getPublisherId();

        if(publisherName == null){
            LOGGER.error("Missing publisher name in the request body");
            throw new IllegalStateException("No publisher name is contained in payload");
        }else if(publisherId == null){
            publisherName = Utility.capitalLetter(publisherName);
            Publisher current = publisherService.getPublisher(publisherName, null);
            if(current == null){
                publisher.setPublisherId(Utility.generateUUID());
                publisher.setName(publisherName);
                LOGGER.debug("Inserting publisher {} to publisher table", publisher.getPublisherId());
                publisherService.addPulbisher(publisher);
            }else{
                publisher = current;
            }
        }
        else{
            publisher = publisherService.getPublisher(null, publisherId);
        }

        if(book.getBookId() == null){
            book.setBookId(Utility.generateUUID());
        }
        book.setType(Utility.getType(book.getGenre().toLowerCase()));

        book.setAuthor(author);
        book.setPublisher(publisher);

        if(bookDAO.hasBook(book.getIsbn()) > 0){
            throw new DuplicationException("Book is already exist in databse");
        }

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
