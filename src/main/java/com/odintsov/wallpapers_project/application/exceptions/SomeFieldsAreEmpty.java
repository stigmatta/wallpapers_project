package com.odintsov.wallpapers_project.application.exceptions;

public class SomeFieldsAreEmpty extends UserCannotBeCreatedException {

    public SomeFieldsAreEmpty() {
        super("Some fields are empty");
    }
}
