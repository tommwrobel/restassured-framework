package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbases.SuiteTestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {

        user = UserTestDataGenerator.generateUser();

        ApiResponse actualApiResponse = given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("user")
        .then()
            .statusCode(200)
            .extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(200);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getId().toString());

        Assertions.assertThat(actualApiResponse)
                .describedAs("Send User was different than received by API")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);
    }

    @AfterTest
    public void cleanUpAfterTest() {
        ApiResponse actualApiResponse = given()
                .contentType("application/json")
            .when()
                .delete("user/{username}", user.getUsername())
            .then()
                .statusCode(200)
                .extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(200);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getUsername());

        Assertions.assertThat(actualApiResponse)
                .describedAs("API Response from system was not as expected")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);
    }
}
