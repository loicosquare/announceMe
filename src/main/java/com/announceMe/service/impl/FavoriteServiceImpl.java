package com.announceMe.service.impl;

import com.announceMe.entity.Favorite;
import com.announceMe.entity.HttpResponse;
import com.announceMe.entity.User;
import com.announceMe.repository.FavoriteRepository;
import com.announceMe.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    @Override
    public HttpResponse<Favorite> addNewFavorite(Favorite favorite) {
        log.info("Adding new favorite to database");
        favorite.setCreatedAt(LocalDateTime.now());
        return HttpResponse.<Favorite>builder()
                .data(Collections.singleton(favoriteRepository.save(favorite)))
                .message("New favorite added Successfully ")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public HttpResponse<Favorite> updateFavorite(Favorite favorite) {
        log.info("Updating favorite in database ");
        Optional<Favorite> favoriteOptional=Optional.of(favoriteRepository.findById(favorite.getId()))
                .orElseThrow(()-> new RuntimeException("favorite not found"));
            Favorite updatedFavorite=favoriteOptional.get();
            updatedFavorite.setUpdatedAt(LocalDateTime.now());
            updatedFavorite.setUser(favorite.getUser());
            updatedFavorite.setAnnounce(favorite.getAnnounce());
            favoriteRepository.save(updatedFavorite);
        return HttpResponse.<Favorite>builder()
                .data(Collections.singleton(updatedFavorite))
                .message("favorite updated successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public HttpResponse<Favorite> getFavoriteById(Long id) {
        log.info("Fetching favorite from database by id {} ",id);
        Optional<Favorite> favoriteOptional=Optional.of(favoriteRepository.findById(id))
                .orElseThrow(()-> new RuntimeException("favorite not found"));
        return HttpResponse.<Favorite>builder()
                .data(Collections.singleton(favoriteOptional.get()))
                .message("Favorite retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build() ;
    }

    @Override
    public HttpResponse<Favorite>  deleteFavorite(Long id) {
        log.info("Fetching favorite from database by id {} ",id);
        Optional<Favorite> favoriteOptional=Optional.of(favoriteRepository.findById(id))
                .orElseThrow(()-> new RuntimeException("favorite not found"));
        favoriteOptional.ifPresent(favoriteRepository::delete);
        return HttpResponse.<Favorite>builder()
                .data(Collections.singleton(favoriteOptional.get()))
                .message("Favorite deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build() ;
    }

    @Override
    public HttpResponse<Favorite>  getAllFavorites() {
        log.info("Fetching all favorites from database ");

            return HttpResponse.<Favorite>builder()
                  .data(favoriteRepository.findAll())
                  .message(favoriteRepository.count() > 0 ? favoriteRepository.count() +"favorites found" :"No favorites found")
                  .status(HttpStatus.OK)
                  .statusCode(HttpStatus.OK.value())
                  .build();

    }
}
