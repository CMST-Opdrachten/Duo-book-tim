package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Configuration
@EnableTransactionManagement
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepo repository) {
        String[] generatedString = new String[2];
        int genAmount = 20;
        for (int i = 0; i < genAmount; i++) {

            for (int j = 0; j < 2; j++) {
                // Create random name and title
                byte[] array = new byte[7]; // length is bounded by 7
                new Random().nextBytes(array);
                generatedString[j] = new String(array, Charset.forName("UTF-8"));
            }
        }

        return args -> {

            /*for (int k = 0; k < genAmount; k++)
                log.info("Preloading " + repository.save(new Book(generatedString[0], generatedString[1])));
*/
        };

    }
}
