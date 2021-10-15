package com.tommwrobel.restassured.main.test.data.pet;

public enum PetsTag {

    YOUNG(1, "young-pet"),
    ADULT(2, "adult-pet");

    private final int id;
    private final String name;

    PetsTag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
