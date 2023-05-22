package com.announceMe.repository;

import com.announceMe.entity.Category;
import com.announceMe.entity.Comment;
import com.announceMe.entity.HttpResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /*void deleteCategoryById(Long id);
    Comment findByComment(String title);*/
}
