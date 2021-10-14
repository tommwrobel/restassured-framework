package com.tommwrobel.restassured.tests.pet;
import com.tommwrobel.restassured.main.pojo.Category;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.pojo.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.Collections.singletonList;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests {

    @BeforeMethod
    public void setuoConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

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
