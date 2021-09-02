package com.oesenkova.marvelapi.service.interfaces;

import com.oesenkova.marvelapi.domain.*;
import com.oesenkova.marvelapi.domain.Character;

public interface CharacterClient {
    PageResult<Character> getCharacterList(CharacterQuery characterQuery);

    Character get(int id);

    PageResult<Comic> getComicsList(int id, ComicsQuery comicsQuery);
}
