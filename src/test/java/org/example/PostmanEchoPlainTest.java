package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostmanEchoPlainTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGet() {
        var response = RestAssured.given()
                .queryParam("foo", "bar")
                .when()
                .get("/get")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код GET должен быть 200");

        String body = response.asString();
        assertTrue(body.contains("\"foo\":\"bar\""), "В теле ответа должен быть параметр foo=bar");
    }

    @Test
    public void testPost() {
        String payload = "{\"name\":\"Alice\",\"age\":30}";

        var response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/post")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код POST должен быть 200");

        String body = response.asString();
        assertTrue(body.contains("\"name\":\"Alice\""), "В теле ответа должен быть name=Alice");
        assertTrue(body.contains("\"age\":30"), "В теле ответа должен быть age=30");
    }

    @Test
    public void testPut() {
        String payload = "{\"name\":\"Bob\",\"age\":25,\"role\":\"user\"}";

        var response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/put")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код PUT должен быть 200");

        String body = response.asString();
        assertTrue(body.contains("\"name\":\"Bob\""), "В теле ответа должен быть name=Bob");
        assertTrue(body.contains("\"age\":25"), "В теле ответа должен быть age=25");
        assertTrue(body.contains("\"role\":\"user\""), "В теле ответа должен быть role=user");
    }

    @Test
    public void testPatch() {
        String payload = "{\"age\":27,\"role\":\"admin\"}";

        var response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/patch")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код PATCH должен быть 200");

        String body = response.asString();
        assertTrue(body.contains("\"age\":27"), "В теле ответа должен быть age=27");
        assertTrue(body.contains("\"role\":\"admin\""), "В теле ответа должен быть role=admin");
    }

    @Test
    public void testDelete() {
        String payload = "{\"reason\":\"test_cleanup\"}";

        var response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .delete("/delete")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код DELETE должен быть 200");

        String body = response.asString();
        assertTrue(body.contains("\"reason\":\"test_cleanup\""), "В теле ответа должен быть reason=test_cleanup");
    }

    @Test
    public void testHead() {
        var response = RestAssured.given()
                .when()
                .head("/head")
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Статус код HEAD должен быть 200");
    }
}