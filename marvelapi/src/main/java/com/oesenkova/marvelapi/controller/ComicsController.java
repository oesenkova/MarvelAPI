package com.oesenkova.marvelapi.controller;

import com.oesenkova.marvelapi.domain.Comics;
import com.oesenkova.marvelapi.domain.ComicsQuery;
import com.oesenkova.marvelapi.domain.PageResult;
import com.oesenkova.marvelapi.service.ComicsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comics")
@Tag(name = "Комиксы Marvel", description = "Взаимодействие с комиксами Marvel: поиск, добавление новых, обновление информации по имеющимся.")
public class ComicsController {

    @Autowired
    private ComicsService comicsService;

    @Operation(summary = "Получение списка комиксов", tags = "Комиксы")
    @GetMapping
    public ResponseEntity<Object> getPage(ComicsQuery comicsQuery) {
        PageResult<Comics> comics = comicsService.getComicsList(comicsQuery);
        return ResponseEntity.ok(comics);
    }

    @Operation(summary = "Получение конкретного комикса по id", tags = "Комиксы")
    @GetMapping("/{id}")
    public ResponseEntity<Object> get (@PathVariable int id){
        return ResponseEntity.ok(comicsService.getComics(id));
    }

    /*@PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user, BindingResult result) throws URISyntaxException {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        user = userService.createUser(user);
        return ResponseEntity.created(new URI("")).body(user);
    }*/
}
