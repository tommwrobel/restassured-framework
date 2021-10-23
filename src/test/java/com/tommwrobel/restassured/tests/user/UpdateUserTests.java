package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.rop.user.DeleteUserEndpoint;
import com.tommwrobel.restassured.main.rop.user.GetUserEndpoint;
import com.tommwrobel.restassured.main.rop.user.PutUserEndpoint;
import com.tommwrobel.restassured.main.test.data.UserTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateUserTests extends SuiteTestBase {

    private User userToUpdate;
    private User userWithNewData;

    @BeforeMethod
    public void createUserAndLogin() {

        userToUpdate = UserTestDataGenerator.generateUser();
        userWithNewData = UserTestDataGenerator.generateUser();
        loginUser(userToUpdate);
    }

    @AfterMethod
    public void cleanUp() {
        new DeleteUserEndpoint().setUserName(userWithNewData.getUsername())
                .sendRequest()
                .assertRequestSuccess();
    }

    @Test
    public void givenUserWhenUpdateExistingUserThenUserIsUpdated() {

        ApiResponse actualApiResponse = new PutUserEndpoint().setUsernameOfUserToUpdate(userToUpdate.getUsername())
                .setUser(userWithNewData)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(userWithNewData.getId().toString())
                .type("unknown")
                .build();

        Assertions.assertThat(actualApiResponse)
                .describedAs("API Response from system was not as expected")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);

        User actualUser = new GetUserEndpoint().setUsername(userWithNewData.getUsername())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        Assertions.assertThat(actualUser)
                .describedAs("API Response from system was not as expected")
                .usingRecursiveComparison()
                .isEqualTo(userWithNewData);

    }
}
