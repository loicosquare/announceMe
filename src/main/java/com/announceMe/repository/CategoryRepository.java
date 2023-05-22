package com.announceMe.repository;

import com.announceMe.entity.Announce;
import com.announceMe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteCategoryById(Long id);
    Category findByLabel(String label);
    //Find all announcements by category
}
