package com.learnify.backend.common.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " with ID: " + resourceId + " not found");
    }
}
