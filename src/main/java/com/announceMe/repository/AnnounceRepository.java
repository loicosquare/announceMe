package com.announceMe.repository;

import com.announceMe.entity.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce, Long> {
    void deleteAnnounceById(Long id);
    Announce findByTitle(String title);
}
