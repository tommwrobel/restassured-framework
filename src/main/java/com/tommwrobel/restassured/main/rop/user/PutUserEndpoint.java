package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.User;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PutUserEndpoint extends BaseEndpoint<PutUserEndpoint, ApiResponse> {

    private User user;
    private String usernameOfUserToUpdate;

    public PutUserEndpoint setUser(User user) {
        this.user = user;
        return this;
    }

    public PutUserEndpoint setUsernameOfUserToUpdate(String usernameOfUserToUpdate) {
        this.usernameOfUserToUpdate = usernameOfUserToUpdate;
        return this;
    }

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public PutUserEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .pathParam("username", usernameOfUserToUpdate)
                .body(user)
        .when()
                .put("user/{username}");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
