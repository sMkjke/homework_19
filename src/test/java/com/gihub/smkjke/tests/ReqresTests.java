package com.gihub.smkjke.tests;

import com.gihub.smkjke.tests.testData.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;


class ReqresTests {

    private static final String URL = "https://reqres.in/api";
    private static final RequestSpecification REQUEST_SPECIFICATION = RestAssured
            .given()
            .log().uri()
            .baseUri(URL)
            .contentType(ContentType.JSON);

    private static final String USER_NAME = "Misha";
    private static final String USER_JOB = "Developer";
    private static final String USER_EMAIL = "smkjke@mail.ru";
    private static final String USER_PASSWORD = "123456";

    @Test
    void checkTotalUsersOnPageTest() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .get("/users?page=1")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("total", is(12));
    }

    @Test
    void checkSingleUserTest() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .get("/user/1")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("data.id", is(1))
                .body("data.pantone_value", is("15-4020"));
    }


    @Test
    void createUserTest() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .body(createUser())
                .when()
                .post("/users")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", is("Misha"))
                .body("job", is("Developer"));
    }

    @Test
    void deleteUserTest() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .body(createUser())
                .when()
                .delete("users/5")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void UnsuccessfullyRegisterUserTest() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .body(createUser())
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }


    private User createUser() {
        return new User(USER_NAME, USER_JOB, USER_EMAIL, USER_PASSWORD);
    }

}
