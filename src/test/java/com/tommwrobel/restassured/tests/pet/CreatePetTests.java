package com.tommwrobel.restassured.tests.pet;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.request.specification.RequestConfigurationBuilder;
import com.tommwrobel.restassured.main.rop.pet.CreatePetEndpoint;
import com.tommwrobel.restassured.main.test.data.PetTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePetTests extends SuiteTestBase {

    private Pet actualPet;

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        // given randomly generated pet
        Pet pet = PetTestDataGenerator.generatePet();

        // when request with pet is send
        CreatePetEndpoint createPetEndpoint = new CreatePetEndpoint();
        actualPet = createPetEndpoint.setPet(pet)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        // then pet is created
        Assertions.assertThat(actualPet)
            .describedAs("Send Pet was different than received by API")
            .usingRecursiveComparison()
            .isEqualTo(pet);
    }

    @AfterTest
    public void cleanUpAfterTest() {
        ApiResponse actualApiResponse = given()
                .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .when()
                .delete("pet/{petId}", actualPet.getId())
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = ApiResponse.builder()
                .code(HttpStatus.SC_OK)
                .type("unknown")
                .message(actualPet.getId().toString())
                .build();

        Assertions.assertThat(actualApiResponse)
            .describedAs("API Response from system was not as expected")
            .usingRecursiveComparison()
            .isEqualTo(expectedApiResponse);
    }
}
