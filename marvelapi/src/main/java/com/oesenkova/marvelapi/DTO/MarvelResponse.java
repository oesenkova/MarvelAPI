package com.oesenkova.marvelapi.DTO;

import lombok.Data;

@Data
public class MarvelResponse<T> {
    private int code;
    private String status;
    private MarvelData<T> data;
}
