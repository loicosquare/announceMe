package com.announceMe.controller;

import com.announceMe.entity.Category;
import com.announceMe.entity.Favorite;
import com.announceMe.entity.HttpResponse;
import com.announceMe.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    @GetMapping(path = "/all")
    public ResponseEntity<HttpResponse<Favorite>> getAllFavorite() {
        return ResponseEntity.ok().body(favoriteService.getAllFavorites());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HttpResponse<Favorite>> getFavoriteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(favoriteService.getFavoriteById(id));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<HttpResponse<Favorite>> addFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok().body(favoriteService.addNewFavorite(favorite));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<HttpResponse<Favorite>> updateFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok().body(favoriteService.updateFavorite(favorite));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpResponse<Favorite>> deleteFavorite(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(favoriteService.deleteFavorite(id));
    }

    //TODO: Add request param for error handling

}
