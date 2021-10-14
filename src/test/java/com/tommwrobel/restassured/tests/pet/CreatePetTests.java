package com.tommwrobel.restassured.tests.pet;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePetTests {

    @BeforeMethod
    public void setuoConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        given()
            .pathParam("petId", 1)
        .when()
            .get("pet/{petId}")
        .then()
            .statusCode(200);

    }
}
