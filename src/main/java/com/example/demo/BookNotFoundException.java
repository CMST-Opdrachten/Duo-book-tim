package com.example.demo;

import java.math.BigInteger;

public class BookNotFoundException  extends RuntimeException{
    BookNotFoundException(BigInteger id) {
        super("Could not find book " + id);
    }
}
