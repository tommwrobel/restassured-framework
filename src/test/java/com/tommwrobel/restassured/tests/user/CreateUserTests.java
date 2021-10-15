package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbases.SuiteTestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {

        user = UserTestDataGenerator.generateUser();

        ApiResponse apiResponse = given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("user")
        .then()
            .statusCode(200)
            .extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), user.getId().toString(), "Message");
    }

    @AfterTest
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given()
                .contentType("application/json")
            .when()
                .delete("user/{username}", user.getUsername())
            .then()
                .statusCode(200)
                .extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), user.getUsername(), "Message");
    }
}
