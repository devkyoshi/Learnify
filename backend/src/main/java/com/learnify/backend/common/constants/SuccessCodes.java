package com.learnify.backend.common.constants;

import lombok.Getter;

@Getter
public enum SuccessCodes {

    USER_CREATED("User created successfully"),
    USER_UPDATED("User updated successfully"),
    USER_DELETED("User deleted successfully"),
    USER_FETCHED("User fetched successfully"),
    COURSE_CREATED("Course created successfully"),
    COURSE_UPDATED("Course updated successfully"),
    COURSE_DELETED("Course deleted successfully");

    private final String message;
    SuccessCodes(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
