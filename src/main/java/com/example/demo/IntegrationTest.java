package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.hamcrest.CoreMatchers.is;

@Profile("integration-test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Test
    public void testBooks() {
        System.out.println("testing");


        get("http://localhost:8080/all")
                .then()
                .assertThat()
                .statusCode(200);

        get("http://localhost:8080/books/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(3));

    }
}
