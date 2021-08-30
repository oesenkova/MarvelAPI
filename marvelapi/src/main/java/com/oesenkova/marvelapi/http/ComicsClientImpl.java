package com.oesenkova.marvelapi.http;

import com.oesenkova.marvelapi.domain.Comics;
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
    public PageResult<Comics> getComicsList(ComicsQuery comicsQuery) {
        return marvelRestClient.getPage(comicsRoute, comicsQuery, Comics.class);
    }

    @Override
    public Comics get(int id) {
        String route = comicsRoute + "/" + id;
        return marvelRestClient.get(route, Comics.class);
    }
}
