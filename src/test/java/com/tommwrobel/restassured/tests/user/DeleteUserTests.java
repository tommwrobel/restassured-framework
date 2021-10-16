package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends SuiteTestBase {

    User user;

    @BeforeMethod
    public void createAndLoginUser() {
        user = createUserToDelete();
        loginUser(user);
    }

    @AfterMethod
    public void logoutUser() {
        logoutUser(user);
    }

    @Test
    public void givenExistingUserUsernameWhenDeleteUserThenUserIsDeleted() {

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

    @Test
    public void  givenNonExistingUserWhenDeletingUserThenUserNotFoundTest() {

        given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .when()
                .delete("user/{username}", UserTestDataGenerator.generateNonExistingUsername())
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private User createUserToDelete() {

        User user = UserTestDataGenerator.generateUser();

        given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(user)
            .when()
                .post("user")
            .then()
                .statusCode(200);

        return user;
    }
}
