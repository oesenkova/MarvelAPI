package com.oesenkova.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterQuery extends BaseQuery {
    private String name;
    private String nameStartsWith;

    public CharacterQuery(int limit, String name) {
        super(0, limit, "");
        this.name = name;
    }
}
