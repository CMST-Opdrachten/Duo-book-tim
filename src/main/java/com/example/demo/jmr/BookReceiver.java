package com.example.demo.jmr;

import com.example.demo.objects.Book;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

@Component
public class BookReceiver {

    @JmsListener(destination = "bookshelf", containerFactory = "aFactory")
    public void receiveMessage(Book book) {
        System.out.println("Received book id:" + book.getId());
        System.out.println("title: " + book.getTitle());
        System.out.println("publisher: " + book.getPublisher());
    }
}
