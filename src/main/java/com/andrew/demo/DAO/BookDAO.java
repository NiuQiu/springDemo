package com.andrew.demo.DAO;

import com.andrew.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookDAO extends CrudRepository<Book, UUID>{
    @Query(value="SELECT COUNT(*) FROM bookstore.book WHERE isbn = ?1", nativeQuery = true)
    public int hasBook(String isbn);
}
