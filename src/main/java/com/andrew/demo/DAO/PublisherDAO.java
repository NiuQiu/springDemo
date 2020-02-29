package com.andrew.demo.DAO;

import com.andrew.demo.model.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Created by Administrator on 2/18/2020.
 */
public interface PublisherDAO extends CrudRepository<Publisher, UUID>{
    @Query(value = "SELECT count(*) FROM bookstore.publisher p where p.publisher_id = ?1", nativeQuery = true)
    int getPublisherCount(UUID publisherId);

    @Query(value="SELECT * from bookstore.publisher where name = ?1", nativeQuery = true)
    Publisher findByName(String publisherName);
}
