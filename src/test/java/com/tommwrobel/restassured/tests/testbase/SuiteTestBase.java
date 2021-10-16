package com.tommwrobel.restassured.tests.testbase;

import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.properties.EnvironmentConfig;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
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

        given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .param("username", user.getUsername())
            .param("password", user.getPassword())
        .when()
            .get("user/login")
        .then()
            .statusCode(HttpStatus.SC_OK);
    }

    protected void logoutUser(User user) {
        given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
        .when()
            .get("user/logout");
    }
}
