package com.tommwrobel.restassured.tests.pet;

import com.tommwrobel.restassured.main.pojo.Category;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.pojo.Tag;
import com.tommwrobel.restassured.tests.testbases.SuiteTestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setPhotoUrls(singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(singletonList(tag));
        pet.setStatus("available");

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
}
