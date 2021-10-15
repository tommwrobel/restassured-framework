package com.tommwrobel.restassured.main.test.data;

import com.tommwrobel.restassured.main.pojo.Category;
import com.tommwrobel.restassured.main.pojo.Pet;
import com.tommwrobel.restassured.main.pojo.Tag;
import com.tommwrobel.restassured.main.test.data.pet.PetsCategory;
import com.tommwrobel.restassured.main.test.data.pet.PetsStatus;
import com.tommwrobel.restassured.main.test.data.pet.PetsTag;

import java.util.Random;

import static java.util.Collections.singletonList;

public class PetTestDataGenerator extends TestDataGenerator {

    public static Pet generatePet() {
        Pet pet = new Pet();
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setName(faker().funnyName().toString());
        pet.setPhotoUrls(singletonList(faker().internet().url()));
        pet.setCategory(getRandomPetsCategory());
        pet.setStatus(getRandomPetsStatus());
        pet.setTags(singletonList(getRandomPetsTag()));
        return pet;
    }

    private static Category getRandomPetsCategory() {
        int pick = new Random().nextInt(PetsCategory.values().length);
        PetsCategory petsCategory = PetsCategory.values()[pick];

        Category category = new Category();
        category.setId(petsCategory.getId());
        category.setName(petsCategory.getName());

        return category;
    }

    private static Tag getRandomPetsTag() {
        int pick = new Random().nextInt(PetsTag.values().length);
        PetsTag petsTag = PetsTag.values()[pick];

        Tag tag = new Tag();
        tag.setId(petsTag.getId());
        tag.setName(petsTag.getName());

        return tag;
    }

    private static String getRandomPetsStatus() {
        int pick = new Random().nextInt(PetsStatus.values().length);
        PetsStatus petsStatus = PetsStatus.values()[pick];

        return petsStatus.toString();
    }
}
