package com.oesenkova.marvelapi.service.interfaces;


import com.oesenkova.marvelapi.domain.Comics;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;

public interface ComicsClient {
    PageResult<Comics> getComicsList(ComicsQuery comicsQuery);

    Comics get(int id);
}
