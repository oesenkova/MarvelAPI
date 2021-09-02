package com.oesenkova.marvelapi.service;

import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.Comic;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.domain.exceptoins.EntityAlreadyExistsException;
import com.oesenkova.marvelapi.domain.exceptoins.NotFoundException;
import com.oesenkova.marvelapi.repository.ComicsRepository;
import com.oesenkova.marvelapi.service.interfaces.ComicsClient;
import com.oesenkova.marvelapi.service.interfaces.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComicsService {
    @Autowired
    private ComicsRepository comicsRepository;

    @Autowired
    private ComicsClient comicsClient;

    @Autowired
    private ResourceClient resourceClient;

    public ComicsService() {
    }

    public PageResult<Comic> getComicsList(ComicsQuery comicsQuery) {
        return comicsClient.getComicsList(comicsQuery);
    }

    public Comic getComic(int id) {
        return comicsClient.get(id);
    }

    public PageResult<Character> getCharactersList(int id, CharacterQuery characterQuery){
        return comicsClient.getCharactersList(id, characterQuery);
    }

    public byte[] getComicImage(int id) {
        Comic comic = getComic(id);
        return resourceClient.getResource(comic.getThumbnail());
    }

    public Comic addComic(Comic comic) throws EntityAlreadyExistsException {
        ComicsQuery comicsQuery = new ComicsQuery(1, comic.getTitle());
        PageResult<Comic> comicsList = getComicsList(comicsQuery);
        if (comicsList.getTotal() > 0){
            int comicId = comicsList.getEntities().get(0).getId().intValue();
            comic = getComic(comicId);
        }

        Optional<Comic> existingComic = comicsRepository.findById(comic.getId());
        if(existingComic.isPresent()){
            throw new EntityAlreadyExistsException("Comic already exists");
        }

        return comicsRepository.save(comic);
    }

    public Comic updateComics(Comic comics) throws NotFoundException {
        Optional<Comic> comic = comicsRepository.findById(comics.getId());
        if (comic.isEmpty()){
            throw new NotFoundException("Comic not found");
        }
        return comicsRepository.save(comics);
    }

    private Comic findById(Long id) {
        var result = comicsRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get();
    }
}
