package com.example.demo;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class DuoRestfullBoekenApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void postTests() {
        System.out.println("testing post");

        String json = "{ \"title\": \"Lord of the rings (Updated)\", \"publisher\": \"J.R.R. Tolkien\" }";
        String json2 = "{ \"title\": \"New book test\", \"publisher\": \"Test publisher\" }";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .put("http://localhost:8080/books/1")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        given()
                .contentType(ContentType.JSON)
                .body(json2)
                .post("http://localhost:8080/books")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        get("http://localhost:8080/books/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("Lord of the rings (Updated)"));

        get("http://localhost:8080/books/2")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    void getTests() {
        System.out.println("testing get");

        get("http://localhost:8080/all")
                .then()
                .assertThat()
                .statusCode(200);

        get("http://localhost:8080/books/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(3));

        get("http://localhost:8080/getAllAsync")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void delTest() {
        System.out.println("testing delete");

        delete("http://localhost:8080/book/2")
                .then()
                .assertThat()
                .statusCode(200);

        get("http://localhost:8080/book/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body(Matchers.equalTo(null));
    }
}

