package com.oesenkova.marvelapi.domain.exceptoins;

import lombok.Data;

@Data
public class ExternalServiceException extends Exception{
    private int statusCode;

    public ExternalServiceException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
