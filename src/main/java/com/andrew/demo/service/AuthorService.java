package com.andrew.demo.service;

import com.andrew.demo.DAO.AuthorDAO;
import com.andrew.demo.model.Author;
import com.andrew.demo.model.Book;
import com.andrew.demo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    public AuthorService() {
    }

    public boolean getAuthor(UUID authorId){
        return authorDAO.getAuthorCount(authorId) > 0;
    }

    public void addAuthor(Author author){
        if(author.getAuthorId() == null){
            author.setAuthorId(Utility.generateUUID());
        }
        authorDAO.save(author);
    }
}
