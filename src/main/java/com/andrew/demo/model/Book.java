package com.andrew.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="book", schema = "bookstore")
public class Book {
    @Id
    @Column(name = "book_id")
    private UUID bookId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;


    @Column(name = "publish_date")
    private BigInteger date;
    private String title;
    private String isbn;
    private String genre;
    private String type;
    @Column(name = "publication_year")
    private int publication;
    private double price;

    public Book() {
    }

    public Author getAuthor() {
        return author;
    }

    @JsonIgnore
    public void setAuthor(Author author) {
        this.author = author;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public BigInteger getDate() {
        return date;
    }

    public void setDate(BigInteger date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @JsonIgnore
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    //    @Override
//    public int hashCode(){
//        final int prime  = 31;
//        int result =1;
//
//        result = prime * result + ((author == null) ? 0 : author.hashCode());
//        result = prime * result + ((category == null) ? 0 : category.hashCode());
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj){
//        if(this == obj) return true;
//        if(obj == null) return false;
//
//        if(getClass() != obj.getClass()) return false;
//
//        Book other = (Book)obj;
//        if(this.author == null){
//            if(other.author != null) return false;
//        } else if(!author.equals(other.author)) return false;
//
//        if(this.category != other.category) return false;
//        if(this.name == null){
//            if(other.name != null) return false;
//        } else if(!name.equals(other.name)) return false;
//        if (book_id == null) {
//            if (other.book_id != null)
//                return false;
//        } else if (!book_id.equals(other.book_id))
//            return false;
//
//        return true;
//    }
}
