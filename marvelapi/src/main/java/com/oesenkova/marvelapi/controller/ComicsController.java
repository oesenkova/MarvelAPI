package com.oesenkova.marvelapi.controller;

import com.oesenkova.marvelapi.domain.CharacterQuery;
import com.oesenkova.marvelapi.domain.Comic;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.domain.exceptoins.EntityAlreadyExistsException;
import com.oesenkova.marvelapi.domain.exceptoins.NotFoundException;
import com.oesenkova.marvelapi.service.ComicsService;
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
@RequestMapping("api/comics")
@Tag(name = "Комиксы Marvel", description = "Взаимодействие с комиксами Marvel: поиск, добавление новых, обновление информации по имеющимся.")
public class ComicsController {

    @Autowired
    private ComicsService comicsService;

    @Operation(summary = "Получение списка комиксов", tags = "Комиксы")
    @GetMapping
    public ResponseEntity<Object> getPage(ComicsQuery comicsQuery) {
        PageResult<Comic> comics = comicsService.getComicsList(comicsQuery);
        return ResponseEntity.ok(comics);
    }

    @Operation(summary = "Получение конкретного комикса по id", tags = "Комиксы")
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        return ResponseEntity.ok(comicsService.getComic(id));
    }

    @Operation(summary = "Получение списка персонажей из конкретного комикса", tags = "Комиксы")
    @GetMapping("/{id}/characters")
    public ResponseEntity<Object> getCharactersList(@PathVariable int id, CharacterQuery characterQuery) {
        PageResult<Character> characters = comicsService.getCharactersList(id, characterQuery);
        return ResponseEntity.ok(characters);
    }

    @Operation(summary = "Получение изображения комикса по id", tags = "Персонажи")
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable int id) {
        byte[] rawData = comicsService.getComicImage(id);
        ByteArrayResource resource = new ByteArrayResource(rawData);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(resource.contentLength())
                .header("Content-Disposition", "attachment; filename=\"hero.jpg\"")
                .body(resource);
    }

    @Operation(summary = "Добавить новый комикс в базу данных", tags = "Комиксы")
    @PostMapping
    public ResponseEntity<Object> addNewComics(@RequestBody Comic comics) throws EntityAlreadyExistsException {
        return ResponseEntity.ok(comicsService.addComic(comics));
    }

    @Operation(summary = "Обновить данные о комиксе в базе данных", tags = "Комиксы")
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Comic comics, BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(comicsService.updateComics(comics));
    }
}
