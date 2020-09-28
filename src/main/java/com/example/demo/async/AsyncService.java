package com.example.demo.async;

import com.example.demo.objects.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<Book>> getAllBooks() throws InterruptedException{
        log.info("getAllBooks starts");

        Book[] response = restTemplate.getForObject("http://localhost:8080/all", Book[].class);
                Thread.sleep(2000);
        log.info("Done.");
        assert response != null;
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Book> getBook(BigInteger id) throws InterruptedException{
        log.info("getAllBooks starts");

        Book response = restTemplate.getForObject("http://localhost:8080/book/{id}", Book.class);
                Thread.sleep(2000);
        log.info("Done.");
        return CompletableFuture.completedFuture(response);
    }

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getBooksPdf() throws InterruptedException{
        log.info("getPDF starts");

        log.info("Done.");
        Thread.sleep(2000);

        ResponseEntity response = restTemplate.getForObject("http://localhost:8080/books/pdf", ResponseEntity.class);

        return CompletableFuture.completedFuture(response);
    }
}
