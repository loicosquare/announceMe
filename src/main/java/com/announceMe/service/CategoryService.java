package com.announceMe.service;

import com.announceMe.entity.Category;
import com.announceMe.entity.Comment;
import com.announceMe.entity.HttpResponse;

import java.util.List;

public interface CategoryService {
    HttpResponse<Category> addNewCategory(Category category);
    HttpResponse<Category> updateCategory(Category category);
    HttpResponse<Category> getCategoryById(Long id);
    HttpResponse<Category> deleteCategory(Long id);
    HttpResponse<Category> getAllCategories();
}
