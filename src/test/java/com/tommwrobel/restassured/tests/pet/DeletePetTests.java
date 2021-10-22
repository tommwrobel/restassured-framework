package com.tommwrobel.restassured.tests.pet;

import com.tommwrobel.restassured.main.pojo.ApiResponse;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.rop.pet.CreatePetEndpoint;
import com.tommwrobel.restassured.main.rop.pet.DeletePetEndpoint;
import com.tommwrobel.restassured.main.test.data.PetTestDataGenerator;
import com.tommwrobel.restassured.tests.testbase.SuiteTestBase;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class DeletePetTests extends SuiteTestBase {

    DeletePetEndpoint deletePetEndpoint = new DeletePetEndpoint();

    @Test
    public void givenExistingPetIdWhenDeletePetThenPetIsDeleted() {

        Pet pet = createPetToDelete();

        ApiResponse actualApiResponse = deletePetEndpoint.setPetId(pet.getId())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

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

        deletePetEndpoint.setPetId(PetTestDataGenerator.generateNonExistingPetId())
                .sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }



    private Pet createPetToDelete() {

        Pet pet = PetTestDataGenerator.generatePet();
        CreatePetEndpoint createPetEndpoint = new CreatePetEndpoint();

        return createPetEndpoint.setPet(pet)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
    }
}
