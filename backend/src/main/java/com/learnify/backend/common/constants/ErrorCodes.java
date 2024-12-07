package com.learnify.backend.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    UNKNOWN_ERROR("Please Contact the Admin"),
    INVALID_REQUEST("Invalid Request"),
    INVALID_CREDENTIALS("Invalid Credentials"),
    USER_NOT_FOUND("User Not Found"),
    USER_ALREADY_EXISTS("User Already Exists"),
    USER_REGISTRATION_FAILED("User Registration Failed"),
    NOT_AUTHORIZED("You are not authorized to perform this action"),

    TEACHER_NOT_FOUND("Teacher is not registered with the system"),

    LEARNING_MATERIAL_NOT_FOUND("Learning Material Not Found"),
    MISSING_FIELDS("Fields are missing or incomplete"),
    COURSE_NOT_FOUND("Course Not Found"),
    COURSE_ALREADY_EXISTS("Course Already Exists");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
