package com.announceMe.repository;

import com.announceMe.entity.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce, Integer> {
    void deleteAnnounceById(Integer id);
    Optional<Announce> findByTitle(String title);
}
