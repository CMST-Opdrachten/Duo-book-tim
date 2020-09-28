package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class LoadDatabase {

    @SuppressWarnings("SameReturnValue")
    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("DB connection for DEV - HSQLDB");
        return "DB Connection for DEV - HSQLDB";
    }

    @SuppressWarnings("SameReturnValue")
    @Profile("production")
    @Bean
    public String prodDatabaseConnection() {
        System.out.println("DB connection for production - postgresDB");
        return "DB connection for production - postgresDB";
    }
}
