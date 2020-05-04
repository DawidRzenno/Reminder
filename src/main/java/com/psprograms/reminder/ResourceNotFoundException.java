package com.psprograms.reminder;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4041L;

    public ResourceNotFoundException(Long id) {
        super("Resource of ID " + id + " could not be found in the database.");
    }
}