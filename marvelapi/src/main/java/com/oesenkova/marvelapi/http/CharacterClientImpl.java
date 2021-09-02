package com.oesenkova.marvelapi.http;

import com.oesenkova.marvelapi.domain.*;
import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.service.interfaces.CharacterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterClientImpl implements CharacterClient {
    @Autowired
    private MarvelRestClient marvelRestClient;

    private final String characterRoute = "characters";

    @Override
    public PageResult<Character> getCharacterList(CharacterQuery characterQuery) {
        return marvelRestClient.getPage(characterRoute, characterQuery, Character.class);
    }

    @Override
    public Character get(int id) {
        String route = characterRoute + "/" + id;
        return marvelRestClient.get(route, Character.class);
    }

    @Override
    public PageResult<Comic> getComicsList(int id, ComicsQuery comicsQuery) {
        String route = characterRoute + "/" + id + "/comics";
        return marvelRestClient.getPage(route, comicsQuery, Comic.class);
    }


}
