package com.announceMe.service;

import com.announceMe.entity.Announce;
import com.announceMe.entity.Favorite;
import com.announceMe.entity.HttpResponse;

import java.util.List;

public interface FavoriteService {
    HttpResponse<Favorite> addNewFavorite(Favorite favorite);
    HttpResponse<Favorite>  updateFavorite(Favorite favorite);
    HttpResponse<Favorite>  getFavoriteById(Long id);
    HttpResponse<Favorite>  deleteFavorite(Long id);
    HttpResponse<Favorite>  getAllFavorites();
}
