package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class LoginUserEndpoint extends BaseEndpoint<LoginUserEndpoint, ApiResponse> {

    private String username;
    private String password;

    public LoginUserEndpoint setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginUserEndpoint setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public LoginUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .param("username", username)
                .param("password", password)
        .when()
                .get("user/login");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
