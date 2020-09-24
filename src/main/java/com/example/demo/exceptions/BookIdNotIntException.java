package com.example.demo.exceptions;

public class BookIdNotIntException extends RuntimeException {
    BookIdNotIntException(String id) {
        super(id + " is not a number");
    }
}
