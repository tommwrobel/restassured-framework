package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, ApiResponse> {

    private String userName;

    public DeleteUserEndpoint setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public DeleteUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .pathParam("userName", userName)
            .when()
                .delete("user/{userName}");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
