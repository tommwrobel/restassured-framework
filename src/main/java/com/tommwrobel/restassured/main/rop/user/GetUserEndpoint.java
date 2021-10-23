package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetUserEndpoint extends BaseEndpoint<GetUserEndpoint, User> {

    private String username;

    public GetUserEndpoint setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    protected Type getModelType() {
        return User.class;
    }

    @Override
    public GetUserEndpoint sendRequest() {

        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .pathParam("username", username)
        .when()
                .get("user/{username}");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
