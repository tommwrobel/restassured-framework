package com.tommwrobel.restassured.tests.user;

import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.tests.testbases.SuiteTestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(123);

        given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("user")
        .then()
            .statusCode(200);
    }
}
