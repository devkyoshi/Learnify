package com.learnify.backend.common.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not found for user: " + message);
    }
    public UserNotFoundException(String role, String username) {
        super("User not found for role: " + role + " and username: " + username);
    }

}
