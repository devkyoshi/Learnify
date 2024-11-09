package com.learnify.backend.common.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not found for user: " + message);
    }

}
