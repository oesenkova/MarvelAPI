package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseQuery {
    private int offset;
    private int limit = 20;
    private String orderBy;
}
