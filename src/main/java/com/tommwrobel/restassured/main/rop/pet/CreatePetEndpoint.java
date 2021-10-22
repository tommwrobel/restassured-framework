package com.tommwrobel.restassured.main.rop.pet;

import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.BaseEndpoint;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreatePetEndpoint extends BaseEndpoint<CreatePetEndpoint, Pet> {

    Pet pet;

    public CreatePetEndpoint setPet(Pet pet) {
        this.pet = pet;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public CreatePetEndpoint sendRequest() {
        response = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(pet)
        .when()
                .post("pet");

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
