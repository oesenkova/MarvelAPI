package com.oesenkova.marvelapi.service;

import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.repository.CharactersRepository;
import com.oesenkova.marvelapi.service.interfaces.CharacterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharactersService {
    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private CharacterClient characterClient;

    public PageResult<Character> getCharacterList(CharacterQuery characterQuery){
        return characterClient.getCharacterList(characterQuery);
    }

    public Character getCharacter(int id){
        return characterClient.get(id);
    }
}
