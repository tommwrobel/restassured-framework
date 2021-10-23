package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.user.CreateUserEndpoint;
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
        CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();

        ApiResponse actualApiResponse = createUserEndpoint.setUser(user)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(user.getId().toString())
                .type("unknown")
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
