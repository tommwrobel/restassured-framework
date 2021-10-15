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
        return Pet.builder()
                .id(faker().number().numberBetween(1, 9999))
                .name(faker().funnyName().name())
                .photoUrls(singletonList(faker().internet().url()))
                .category(getRandomPetsCategory())
                .status(getRandomPetsStatus())
                .tags(singletonList(getRandomPetsTag()))
                .build();
    }

    private static Category getRandomPetsCategory() {
        int pick = new Random().nextInt(PetsCategory.values().length);
        PetsCategory petsCategory = PetsCategory.values()[pick];

        return Category.builder()
                .id(petsCategory.getId())
                .name(petsCategory.getName())
                .build();
    }

    private static Tag getRandomPetsTag() {
        int pick = new Random().nextInt(PetsTag.values().length);
        PetsTag petsTag = PetsTag.values()[pick];

        return Tag.builder()
                .id(petsTag.getId())
                .name(petsTag.getName())
                .build();
    }

    private static String getRandomPetsStatus() {
        int pick = new Random().nextInt(PetsStatus.values().length);
        PetsStatus petsStatus = PetsStatus.values()[pick];

        return petsStatus.toString();
    }
}
