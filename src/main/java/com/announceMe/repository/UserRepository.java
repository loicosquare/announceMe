package com.announceMe.repository;

import com.announceMe.entity.Favorite;
import com.announceMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*public List<User> findAllUsers();
    void deleteUserById(Long id);*/
}
