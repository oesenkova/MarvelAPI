package com.oesenkova.marvelapi.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MarvelData<T> {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private ArrayList<T> results;
}
