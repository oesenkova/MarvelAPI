package com.oesenkova.marvelapi.domain.exceptoins;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
