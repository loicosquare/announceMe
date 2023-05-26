package com.announceMe.repository;

import com.announceMe.entity.Favorite;
import com.announceMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*public List<User> findAllUsers();
    void deleteUserById(Long id);*/
    Optional<User> findByEmail(String email);
}
