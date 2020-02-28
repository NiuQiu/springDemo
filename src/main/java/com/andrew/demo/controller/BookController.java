package com.andrew.demo.controller;

import com.andrew.demo.model.Book;
import com.andrew.demo.model.PostBody;
import com.andrew.demo.model.PostResponse;
import com.andrew.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Controller
@Path("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    private final static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        return Response.ok().entity(bookService.getAllBooks()).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{bookId}")
    public Response getBook(@PathParam("bookId") String bookId) {
        try{
            Book book = bookService.getBook(bookId);
            if(book == null){
                LOGGER.debug("Cannot to find book {}", bookId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).entity(book).build();
        }catch(IllegalArgumentException ie){
            LOGGER.error("Error when looking for book {} with {}", bookId, ie.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    @Path("/init")
    public Response addBook(PostBody payload) {
        String bookTitle = payload.getBook().getTitle();
        try{

            PostResponse res = new PostResponse(bookService.addBook(payload));
            LOGGER.info("Adding bookTitle {} successful", bookTitle);
            return Response.status(Response.Status.CREATED).entity(res).build();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("Adding bookTitle {} failed {}", bookTitle, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
