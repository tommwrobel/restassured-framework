package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.rop.user.CreateUserEndpoint;
import com.tommwrobel.restassured.main.rop.user.DeleteUserEndpoint;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        DeleteUserEndpoint deleteUserEndpoint = new DeleteUserEndpoint();

        ApiResponse actualApiResponse = deleteUserEndpoint.setUserName(user.getUsername())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

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

        DeleteUserEndpoint deleteUserEndpoint = new DeleteUserEndpoint();

        deleteUserEndpoint.setUserName(UserTestDataGenerator.generateNonExistingUsername())
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }

    private User createUserToDelete() {

        CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();
        User user = UserTestDataGenerator.generateUser();

        createUserEndpoint.setUser(user)
                .sendRequest()
                .assertRequestSuccess();

        return user;
    }
}
