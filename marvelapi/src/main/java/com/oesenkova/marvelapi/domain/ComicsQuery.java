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
    private int startYear;
    private int issueNumber;
    private int characters;
    private int series;
}
