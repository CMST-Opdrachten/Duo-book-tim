package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAsync
@RestController
public class DuoRestfullBoekenApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuoRestfullBoekenApplication.class, args);


    }


}
