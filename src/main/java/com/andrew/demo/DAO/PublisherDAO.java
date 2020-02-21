package com.andrew.demo.DAO;

import com.andrew.demo.model.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by Administrator on 2/18/2020.
 */
public interface PublisherDAO extends CrudRepository<Publisher, UUID>{
    @Query(value = "SELECT count(*) FROM bookstore.publisher p where p.publisher_id = ?1", nativeQuery = true)
    public int getPublisherCount(UUID publisherId);
}
