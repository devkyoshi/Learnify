package com.learnify.backend.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    UNKNOWN_ERROR("Please Contact the Admin"),
    INVALID_REQUEST("Invalid Request"),
    INVALID_CREDENTIALS("Invalid Credentials"),
    USER_NOT_FOUND("User Not Found"),
    USER_ALREADY_EXISTS("User Already Exists"),
    USER_REGISTRATION_FAILED("User Registration Failed");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
