package com.orangehrm.tests.utils;

import org.apache.commons.text.RandomStringGenerator;

public class ValuesGenerator {
    public static String randomString() {
        return randomString(10);
    }

    public static String randomString(int length) {
        return new RandomStringGenerator.Builder()
                .withinRange('A', 'z')
                .filteredBy(Character::isLetter)
                .build().generate(length);
    }

    public static String randomEmail() {
        return randomString(8) + "@example.com";
    }
}
