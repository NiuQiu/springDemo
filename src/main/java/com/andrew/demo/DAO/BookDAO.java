package com.andrew.demo.DAO;

import com.andrew.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookDAO extends CrudRepository<Book, UUID>{
//    @Query(value="SELECT * FROM bookstore.book b JOIN FETCH where b.book_id = :bookId")
//    public Book getBookById(@Param("bookId") UUID bookId);
}
