package com.learnify.backend.common.exceptions;

public class CourseAlreadyExistException extends RuntimeException {
    public CourseAlreadyExistException(String message) {
        super("Course already exist with name: " + message);
    }
}
