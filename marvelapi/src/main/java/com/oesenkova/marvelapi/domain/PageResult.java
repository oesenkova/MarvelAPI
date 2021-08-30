package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PageResult<T> {
    private int total;
    private ArrayList<T> entities;
}
