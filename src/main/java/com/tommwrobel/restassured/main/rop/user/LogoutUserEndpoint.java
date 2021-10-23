package com.tommwrobel.restassured.main.rop.user;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class LogoutUserEndpoint extends BaseEndpoint<LogoutUserEndpoint, ApiResponse> {
    @Override
    protected Type getModelType() {
        return null;
    }

    @Override
    public LogoutUserEndpoint sendRequest() {

        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .when()
                .get("user/logout");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
