package com.oesenkova.marvelapi.service.interfaces;

import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.Comic;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;

public interface ComicsClient {
    PageResult<Comic> getComicsList(ComicsQuery comicsQuery);

    Comic get(int id);

    PageResult<Character> getCharactersList(int id, CharacterQuery characterQuery);
}
