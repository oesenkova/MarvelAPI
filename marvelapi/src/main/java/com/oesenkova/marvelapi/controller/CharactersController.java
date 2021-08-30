package com.oesenkova.marvelapi.controller;

import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.service.CharactersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/characters")
@Tag(name = "Персонажи", description = "Взаимодействие с персонажами Marvel: поиск, добавление новых, обновление информации по имеющимся.")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @Operation(summary = "Получение списка персонажей", tags = "Персонажи")
    @GetMapping
    public ResponseEntity<Object> getPage(CharacterQuery characterQuery) {
        PageResult<Character> characters = charactersService.getCharacterList(characterQuery);
        return ResponseEntity.ok(characters);
    }

    @Operation(summary = "Получение конкретного персонажа по id", tags = "Персонажи")
    @GetMapping("/{id}")
    public ResponseEntity<Object>get(@PathVariable int id){
        return ResponseEntity.ok(charactersService.getCharacter(id));
    }
}
