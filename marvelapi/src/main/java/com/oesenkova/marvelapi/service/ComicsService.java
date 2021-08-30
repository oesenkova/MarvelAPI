package com.oesenkova.marvelapi.service;

import com.oesenkova.marvelapi.domain.Comics;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.repository.ComicsRepository;
import com.oesenkova.marvelapi.service.interfaces.ComicsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicsService {
    @Autowired
    private ComicsRepository comicsRepository;

    @Autowired
    private ComicsClient comicsClient;

    public ComicsService() {
    }

    public PageResult<Comics> getComicsList (ComicsQuery comicsQuery){
        return comicsClient.getComicsList(comicsQuery);
    }

    public Comics getComics (int id){
        return comicsClient.get(id);
    }
}
