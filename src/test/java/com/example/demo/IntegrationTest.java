package com.example.demo;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;


//@Profile("integration-test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    BigInteger id;

    @Test
    void integrationTests() {

        postTests();
        getTests();
        delTest();

    }

    void postTests() {
        System.out.println("testing post");

        String json1 = "{ \"title\": \"Lord of the rings (Updated)\", \"publisher\": \"J.R.R. Tolkien\" }";
        String json2 = "{ \"title\": \"New book test\", \"publisher\": \"Test publisher\" }";

        Integer id = given()
                .contentType(ContentType.JSON)
                .body(json2)
                .post("http://localhost:8080/books")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

        this.id = BigInteger.valueOf(id);

        given()
                .contentType(ContentType.JSON)
                .body(json1)
                .put("http://localhost:8080/books/" + this.id)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        get("http://localhost:8080/books/" + this.id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("Lord of the rings (Updated)"));
    }


    void getTests() {
        System.out.println("testing get");

        get("http://localhost:8080/all")
                .then()
                .assertThat()
                .statusCode(200);

        get("http://localhost:8080/books/" + this.id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(3));

        get("http://localhost:8080/getAllAsync")
                .then()
                .assertThat()
                .statusCode(200);
    }

    void delTest() {
        System.out.println("testing delete");

        delete("http://localhost:8080/book/" + id)
                .then()
                .assertThat()
                .statusCode(200);

    }

}
