package com.learnify.backend.common.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exists");
    }
    public UserAlreadyExistException(String username) {
        super("User " + username + " already exists");
    }
}
