package org.example; // Важно: должно совпадать с папкой src/test/java/org/example

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanEchoPlainTest {

    private static final String BASE_URL = "https://postman-echo.com";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGet() {
        var response = RestAssured.given()
                .queryParam("foo", "bar")
                .when()
                .get("/get")
                .then()
                .extract().response();

        int status = response.getStatusCode();

        // Используем стандартный Assert вместо ручного throw
        assertEquals(200, status, "Статус код должен быть 200");

        // Можно добавить проверку, что в теле ответа есть наш параметр foo=bar
        // assertTrue(body.contains("\"foo\":\"bar\""));
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

        // Проверка, что сервер вернул то, что мы отправили (echo)
        // assertTrue(body.contains("\"name\":\"Alice\""));
    }
}