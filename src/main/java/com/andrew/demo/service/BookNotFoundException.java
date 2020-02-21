package com.andrew.demo.service;

/**
 * Created by Administrator on 2/4/2020.
 */
public class BookNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -8069763198347163909L;

    public BookNotFoundException(String message) {
        super(message);
    }
}
