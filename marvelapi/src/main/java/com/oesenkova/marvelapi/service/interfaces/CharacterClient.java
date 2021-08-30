package com.oesenkova.marvelapi.service.interfaces;

import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.PageResult;

public interface CharacterClient {
    PageResult<Character> getCharacterList(CharacterQuery characterQuery);

    Character get(int id);
}
