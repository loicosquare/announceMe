package com.announceMe.repository;

import com.announceMe.entity.Category;
import com.announceMe.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite deleteFavoriteById(Long id);
}
