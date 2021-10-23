package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreateUserEndpoint extends BaseEndpoint<CreateUserEndpoint, ApiResponse> {

    private User user;

    public CreateUserEndpoint setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public CreateUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(user)
            .when()
                .post("user");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
