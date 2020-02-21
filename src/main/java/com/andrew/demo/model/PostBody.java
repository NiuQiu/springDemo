package com.andrew.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2/18/2020.
 */
public class PostBody {
    private Book book;
    private Publisher publisher;
    private Author author;

    public PostBody() {
    }

    public Book getBook() {
        return book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }
}
