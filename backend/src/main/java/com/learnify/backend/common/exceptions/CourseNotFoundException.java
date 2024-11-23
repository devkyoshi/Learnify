package com.learnify.backend.common.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long courseID, String courseName) {
        super( "Course with ID: " + courseID + " and Name: " + courseName + " not found");
    }
}
