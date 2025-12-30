package com.odintsov.wallpapers_project.domain.enums;

public final class UserFields {
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phoneNumber";

    private UserFields() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
