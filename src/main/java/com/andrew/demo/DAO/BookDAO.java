package com.andrew.demo.DAO;

import com.andrew.demo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookDAO extends CrudRepository<Book, UUID>{
}
