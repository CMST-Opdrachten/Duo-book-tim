package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Book {

    private @Id @GeneratedValue BigInteger id;
    private String title;
    private String publisher;

    Book() {}

    Book(String title, String publisher) {

        this.title = title;
        this.publisher = publisher;
    }

    public BigInteger getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Book book = (Book) o;
        return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title)
                && Objects.equals(this.publisher, book.publisher);
    }
}
