package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class DuoRestfullBoekenApplicationTests {

    @Test
    void contextLoads() {
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
