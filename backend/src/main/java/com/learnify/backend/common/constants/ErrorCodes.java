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

    TEACHER_NOT_FOUND("Teacher is not registered with the system"),

    COURSE_WITH_MISSING_FIELDS("Course details are missing or incomplete"),
    COURSE_ALREADY_EXISTS("Course Already Exists");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
