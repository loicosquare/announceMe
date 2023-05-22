package com.announceMe.service;

import com.announceMe.entity.Comment;
import com.announceMe.entity.Favorite;

import java.util.List;

public interface CommentService {
    Comment addNewComment(Comment comment);
    Comment updateComment(Comment comment);
    Comment getCommentById(int id);
    void deleteComment(Long id);
    List<Comment> getAllComments();
}
