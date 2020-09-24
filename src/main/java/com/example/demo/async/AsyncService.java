package com.example.demo.async;

import com.example.demo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private static Logger log = LoggerFactory.getLogger(AsyncService.class);
    private List<Book> booksList;

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
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }
}
