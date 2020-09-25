package com.example.demo.async;

import com.example.demo.objects.Book;
import com.itextpdf.text.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class AsyncController {

    private static Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService service;

    @RequestMapping(value =  "/getAllAsync", method = RequestMethod.GET)
    public List<Book> getAllAsync() throws InterruptedException, ExecutionException {
        log.info("testAsync starting");

        CompletableFuture<List<Book>> book = service.getAllBooks();

        CompletableFuture.allOf(book).join();
        log.info(String.valueOf(book.get()));
        return book.get();
    }

    @RequestMapping(value =  "/getPdf", method = RequestMethod.GET)
    public String getPdf() throws InterruptedException, ExecutionException {
        log.info("test pdf starting");

        CompletableFuture<String> doc = service.getPdf();

        CompletableFuture.allOf(doc).join();
        return doc.get();
    }

}
