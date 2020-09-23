package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Configuration
@ConfigurationProperties("spring.datasource")
public class LoadDatabase {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("DB connection for DEV - HSQLDB");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection for DEV - HSQLDB";
    }

    @Profile("production")
    @Bean
    public String prodDatabaseConnection() {
        System.out.println("DB connection for production - postgresDB");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for production - postgresDB";
    }
}
