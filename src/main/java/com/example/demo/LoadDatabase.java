package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepo repository) {
        return args -> {
            log.info ("Preloading " + repository.save(new Book("The Hobbit", "J.R.R Tolkien")));
            log.info ("Preloading " + repository.save(new Book("The Lord of the Rings", "J.R.R Tolkien")));
            log.info ("Preloading " + repository.save(new Book("Beowulf: The Monsters and the Critics", "J.R.R Tolkien")));
            log.info ("Preloading " + repository.save(new Book("Mortal Engines", "Philip Reeve")));
        };
    }
}
