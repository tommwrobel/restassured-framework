package com.tommwrobel.restassured.main.test.data;

import com.tommwrobel.restassured.main.pojo.User;

public class UserTestDataGenerator extends TestDataGenerator {

    public static User generateUser() {
        return User.builder()
                .id(faker().number().numberBetween(1, 9999))
                .username(faker().name().username())
                .firstName(faker().name().firstName())
                .lastName(faker().name().lastName())
                .email(faker().internet().emailAddress())
                .password("P@ssw0rd")
                .phone(faker().phoneNumber().phoneNumber())
                .userStatus(1)
                .build();
    }

    public static String generateNonExistingUsername() {
        return faker().name().username() + "nonExisting";
    }
}
