package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .type("unknown")
                .message(user.getId().toString())
                .build();

        Assertions.assertThat(actualApiResponse)
                .describedAs("Send User was different than received by API")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);
    }

    @AfterTest
    public void cleanUpAfterTest() {
        ApiResponse actualApiResponse = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .when()
                .delete("user/{username}", user.getUsername())
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .type("unknown")
                .message(user.getUsername())
                .build();

        Assertions.assertThat(actualApiResponse)
                .describedAs("API Response from system was not as expected")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);
    }
}
