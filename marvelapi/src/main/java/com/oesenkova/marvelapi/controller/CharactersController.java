package com.oesenkova.marvelapi.controller;

import com.oesenkova.marvelapi.domain.*;
import com.oesenkova.marvelapi.domain.Character;
import com.oesenkova.marvelapi.domain.exceptoins.EntityAlreadyExistsException;
import com.oesenkova.marvelapi.domain.exceptoins.NotFoundException;
import com.oesenkova.marvelapi.service.CharactersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

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
    public ResponseEntity<Object> get(@PathVariable int id) {
        return ResponseEntity.ok(charactersService.getCharacter(id));
    }

    @Operation(summary = "Получение списка комиксов с конкретным персонажем", tags = "Персонажи")
    @GetMapping("/{id}/comics")
    public ResponseEntity<Object> getComicsList(@PathVariable int id, ComicsQuery comicsQuery) {
        PageResult<Comic> comicsList = charactersService.getComicsList(id, comicsQuery);
        return ResponseEntity.ok(comicsList);
    }

    @Operation(summary = "Получение изображения конкретного персонажа по id", tags = "Персонажи")
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable int id) {
        byte[] rawData = charactersService.getCharacterImage(id);
        ByteArrayResource resource = new ByteArrayResource(rawData);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(resource.contentLength())
                .header("Content-Disposition", "attachment; filename=\"hero.jpg\"")
                .body(resource);
    }

    @Operation(summary = "Добавить нового персонажа в базу данных", tags = "Персонажи")
    @PostMapping
    public ResponseEntity<Object> addNewCharacter(@RequestBody Character character) throws EntityAlreadyExistsException {
        return ResponseEntity.ok(charactersService.addCharacter(character));
    }

    @Operation(summary = "Обновить данные о персонаже в базе данных", tags = "Персонажи")
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Character character, BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(charactersService.updateCharacter(character));
    }
}
