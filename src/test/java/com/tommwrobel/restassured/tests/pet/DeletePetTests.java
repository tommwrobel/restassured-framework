package com.tommwrobel.restassured.tests.pet;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.test.data.PetTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePetTests extends SuiteTestBase {

    @Test
    public void givenExistingPetIdWhenDeletePetThenPetIsDeleted() {

        Pet pet = createPetToDelete();

        ApiResponse actualApiResponse = given()
                    .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when()
                    .delete("pet/{petId}", pet.getId())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .type("unknown")
                .message(pet.getId().toString())
                .build();

        Assertions.assertThat(actualApiResponse)
                .describedAs("API Response from system was not as expected")
                .usingRecursiveComparison()
                .isEqualTo(expectedApiResponse);
    }

    @Test
    public void  givenNonExistingPetWhenDeletingPetThenPetNotFoundTest() {

        given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
        .when()
            .delete("pet/{petId}", PetTestDataGenerator.generateNonExistingPetId())
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }



    private Pet createPetToDelete() {

        Pet pet = PetTestDataGenerator.generatePet();

        given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(pet)
                .when()
                .post("pet")
                .then()
                .statusCode(200);

        return pet;
    }
}
