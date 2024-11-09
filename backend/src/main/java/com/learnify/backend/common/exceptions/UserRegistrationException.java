package com.learnify.backend.common.exceptions;

public class UserRegistrationException  extends RuntimeException {
    public UserRegistrationException() {
        super("User registration failed");
    }
    public UserRegistrationException(String username) {
        super("User registration failed for " + username);
    }
}
