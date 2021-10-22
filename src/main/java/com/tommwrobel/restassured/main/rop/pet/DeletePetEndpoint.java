package com.tommwrobel.restassured.main.rop.pet;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeletePetEndpoint extends BaseEndpoint<DeletePetEndpoint, ApiResponse> {

    private int petId;

    public DeletePetEndpoint setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public DeletePetEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .pathParam("petId", String.valueOf(petId))
        .when()
                .delete("pet/{petId}");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
