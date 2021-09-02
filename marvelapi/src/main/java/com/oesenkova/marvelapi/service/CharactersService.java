package com.oesenkova.marvelapi.service;

import com.oesenkova.marvelapi.domain.*;
import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.domain.exceptoins.EntityAlreadyExistsException;
import com.oesenkova.marvelapi.domain.exceptoins.NotFoundException;
import com.oesenkova.marvelapi.repository.CharactersRepository;
import com.oesenkova.marvelapi.service.interfaces.CharacterClient;
import com.oesenkova.marvelapi.service.interfaces.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharactersService {
    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private CharacterClient characterClient;

    @Autowired
    private ResourceClient resourceClient;

    public PageResult<Character> getCharacterList(CharacterQuery characterQuery) {
        return characterClient.getCharacterList(characterQuery);
    }

    public Character getCharacter(int id) {
        return characterClient.get(id);
    }

    public PageResult<Comic> getComicsList(int id, ComicsQuery comicsQuery) {
        return characterClient.getComicsList(id, comicsQuery);
    }

    public Character addCharacter(Character character) throws EntityAlreadyExistsException {
        CharacterQuery characterQuery = new CharacterQuery(1, character.getName());
        PageResult<Character> charactersList = getCharacterList(characterQuery);
        if (charactersList.getTotal() > 0) {
            int characterId = charactersList.getEntities().get(0).getId().intValue();
            character = getCharacter(characterId);
        }

        Optional<Character> existingCharacter = charactersRepository.findById(character.getId());
        if(existingCharacter.isPresent()){
            throw new EntityAlreadyExistsException("Character already exists");
        }

        return charactersRepository.save(character);
    }

    public Character updateCharacter(Character character) throws NotFoundException {
        Optional<Character> existingCharacter = charactersRepository.findById(character.getId());
        if (existingCharacter.isEmpty()){
            throw new NotFoundException("Character not found");
        }
        return charactersRepository.save(character);
    }

    public byte[] getCharacterImage(int id) {
        Character character = getCharacter(id);
        return resourceClient.getResource(character.getThumbnail());
    }
}
