package com.tommwrobel.restassured.tests.testbase;

import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.properties.EnvironmentConfig;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.user.LoginUserEndpoint;
import com.tommwrobel.restassured.main.rop.user.LogoutUserEndpoint;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class SuiteTestBase {

    @BeforeSuite
    public void setupConfiguration() {

        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        RestAssured.baseURI = environmentConfig.baseUri();
        RestAssured.basePath = environmentConfig.basePath();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    protected void loginUser(User user) {

        new LoginUserEndpoint().setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .sendRequest()
                .assertRequestSuccess();
    }

    protected void logoutUser() {

        new LogoutUserEndpoint()
                .sendRequest()
                .assertRequestSuccess();
    }
}
