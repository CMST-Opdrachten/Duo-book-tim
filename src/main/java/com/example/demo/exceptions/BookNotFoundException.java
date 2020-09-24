package com.example.demo.exceptions;

import java.math.BigInteger;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(BigInteger id) {
        super("Could not find book " + id);
    }
}