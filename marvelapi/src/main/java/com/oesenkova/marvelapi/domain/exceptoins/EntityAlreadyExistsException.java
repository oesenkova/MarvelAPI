package com.oesenkova.marvelapi.domain.exceptoins;

public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
