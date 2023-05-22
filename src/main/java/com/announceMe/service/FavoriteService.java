package com.announceMe.service;

import com.announceMe.entity.Announce;
import com.announceMe.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite addNewFavorite(Favorite favorite);
    Favorite updateAnnounce(Favorite favorite);
    Favorite getFavoriteById(int id);
    void deleteFavorite(Long id);
    List<Favorite> getAllFavorites();
}
