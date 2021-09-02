package com.oesenkova.marvelapi.http;

import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.Comic;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.service.interfaces.ComicsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicsClientImpl implements ComicsClient {
    @Autowired
    private MarvelRestClient marvelRestClient;

    private final String comicsRoute = "comics";

    @Override
    public PageResult<Comic> getComicsList(ComicsQuery comicsQuery) {
        return marvelRestClient.getPage(comicsRoute, comicsQuery, Comic.class);
    }

    @Override
    public Comic get(int id) {
        String route = comicsRoute + "/" + id;
        return marvelRestClient.get(route, Comic.class);
    }

    @Override
    public PageResult<Character> getCharactersList(int id, CharacterQuery characterQuery) {
        String route = comicsRoute + "/" + id + "/characters";
        return marvelRestClient.getPage(route, characterQuery, Character.class);
    }
}
