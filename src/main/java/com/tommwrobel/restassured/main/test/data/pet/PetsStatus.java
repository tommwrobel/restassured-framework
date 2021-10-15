package com.tommwrobel.restassured.main.test.data.pet;

import java.util.Random;

public enum PetsStatus {

    AVAILABLE("available"),
    SOLD("sold"),
    PENDING("pending");

    private final String name;

    PetsStatus(String name) {
        this.name = name;
    }

    public PetsStatus randomPetsStatus() {
        int pick = new Random().nextInt(PetsStatus.values().length);
        return PetsStatus.values()[pick];
    }

    public String getName() {
        return name;
    }
}
