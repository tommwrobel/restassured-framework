package com.tommwrobel.restassured.main.test.data.pet;

import java.util.Random;

public enum PetsCategory {

    DOG(1, "dog"),
    CAT(2, "cat"),
    OTHER(3, "other");

    private final String name;
    private final int id;

    PetsCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PetsCategory randomPetsCategory() {
        int pick = new Random().nextInt(PetsCategory.values().length);
        return PetsCategory.values()[pick];
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
