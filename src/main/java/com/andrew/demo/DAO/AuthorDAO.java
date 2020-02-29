package com.andrew.demo.DAO;

import com.andrew.demo.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorDAO extends CrudRepository<Author, UUID>{
    @Query(value = "SELECT count(*) FROM bookstore.author a where a.author_id = :authorId", nativeQuery = true)
    public int getAuthorCount(@Param("authorId") UUID authorId);

    @Query(value = "SELECT * FROM bookstore.author where first_name = ?1 AND last_name = ?2", nativeQuery = true)
    public Author findByFullName(String fName, String lName);
}
