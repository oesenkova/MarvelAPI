package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComicsQuery extends BaseQuery{
    private String title;
    private String titleStartsWith;

    public ComicsQuery(int limit, String title) {
        super(0, limit, "");
        this.title = title;
    }
}
