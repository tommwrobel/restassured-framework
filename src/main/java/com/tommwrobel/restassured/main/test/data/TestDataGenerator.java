package com.tommwrobel.restassured.main.test.data;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static Faker faker() {
        return Faker.instance();
    }
}
