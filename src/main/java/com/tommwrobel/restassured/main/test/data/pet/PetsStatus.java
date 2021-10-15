package com.tommwrobel.restassured.main.test.data.pet;

public enum PetsStatus {

    AVAILABLE("available"),
    SOLD("sold"),
    PENDING("pending");

    private final String name;

    PetsStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
