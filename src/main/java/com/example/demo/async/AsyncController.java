package com.example.demo.async;

import com.example.demo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class AsyncController {

    private static Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService service;

    @RequestMapping(value =  "/testAsync", method = RequestMethod.GET)
    public Book testAsync() throws InterruptedException, ExecutionException {
        log.info("testAsync starting");

        CompletableFuture<Book> book = service.getAllBooks();

        CompletableFuture.allOf(book).join();
        log.info(String.valueOf(book.get()));
        return book.get();
    }

}
