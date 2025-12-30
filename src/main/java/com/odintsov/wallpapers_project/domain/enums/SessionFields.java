package com.odintsov.wallpapers_project.domain.enums;

public class SessionFields {
    public static final String TOKEN = "token";
    public static final String EXPIRES_AT = "expiresAt";

    private SessionFields() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
