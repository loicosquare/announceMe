package com.announceMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.announceMe.entity.Announce;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce, Long> {
}
