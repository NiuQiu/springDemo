package com.andrew.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "publisher", schema = "bookstore")
public class Publisher {
    @Id
    private UUID publisherId;
    private String country;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> pbBooks;

    public Publisher() {
    }

    public UUID getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(UUID publisherId) {
        this.publisherId = publisherId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Book> getPbBooks() {
        return pbBooks;
    }

    public void setPbBooks(Set<Book> pbBooks) {
        this.pbBooks = pbBooks;
    }
}
