package com.learnify.backend.common.exceptions;

public class FieldsEmptyException extends RuntimeException {
    public FieldsEmptyException(String message) {
        super(message);
    }

    public FieldsEmptyException(String missingField , String message) {
        super("Field: " + missingField + " is empty. " + message);
    }
}
