package com.example.demo;

public class BookIdNotIntException extends RuntimeException {
    BookIdNotIntException(String id) {
        super(id + " is not a number");
    }
}
