package com.tommwrobel.restassured.tests.pet;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.test.data.PetTestDataGenerator;
import com.tommwrobel.restassured.tests.testbases.SuiteTestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    private Pet pet;

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        PetTestDataGenerator petTestDataGenerator = new PetTestDataGenerator();
        pet = petTestDataGenerator.generatePet();

        Pet actualPet = given()
                .body(pet)
                .contentType("application/json")
            .when()
                .post("pet")
            .then()
                .statusCode(200)
                .extract().as(Pet.class);

        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name");
    }

    @AfterTest
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given()
                .contentType("application/json")
                .when()
                .delete("pet/{petId}", pet.getId())
                .then()
                .statusCode(200)
                .extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), pet.getId().toString(), "Message");
    }
}
