package com.announceMe.service.impl;

import com.announceMe.entity.Announce;
import com.announceMe.entity.Category;
import com.announceMe.entity.Favorite;
import com.announceMe.entity.HttpResponse;
import com.announceMe.repository.CategoryRepository;
import com.announceMe.service.CategoryService;
import com.announceMe.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public HttpResponse<Category> addNewCategory(Category category) {
        log.info("Adding new category to database");
        category.setCreatedAt(LocalDateTime.now());
        return HttpResponse.<Category>builder()
                .data(Collections.singleton(categoryRepository.save(category)))
                .message("New category added successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                //.timeStamp(LocalDateTime.now().format())
                .build();
    }

    @Override
    public HttpResponse<Category> updateCategory(Category category) {
        Optional<Category> optionalCategory = Optional.of(categoryRepository.findById(category.getId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        log.info("Updating category in database");
        Category updatedCategory = optionalCategory.get();
        updatedCategory.setId(updatedCategory.getId());
        updatedCategory.setLabel(category.getLabel());
        updatedCategory.setDescription(category.getDescription());
        updatedCategory.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(updatedCategory);
        return HttpResponse.<Category>builder()
                .data(Collections.singleton(updatedCategory))
                .message("Category updated successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //.timeStamp()
                .build();
    }

    @Override
    public HttpResponse<Category> getCategoryById(Long id) {
        log.info("Fetching category from database by id {}", id);
        Optional<Category> optionalCategory = Optional.of(categoryRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return HttpResponse.<Category>builder()
                .data(Collections.singleton(optionalCategory.get()))
                .message("Category retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //.timeStamp()
                .build();
    }

    @Override
    public HttpResponse<Category> deleteCategory(Long id) {
        log.info("Deleting category from database by id {}", id);
        Optional<Category> optionalCategory = Optional.of(categoryRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        /*if(optionalCategory.isPresent()){
            categoryRepository.deleteById(optionalCategory.get().getId());
        }*/
        optionalCategory.ifPresent(categoryRepository::delete);
        return HttpResponse.<Category>builder()
                .data(Collections.singleton(optionalCategory.get()))
                .message("Category updated successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //.timeStamp()
                .build();
    }

    @Override
    public HttpResponse<Category> getAllCategories() {
        log.info("Fetching all categories from database");
        return HttpResponse.<Category>builder()
                .data(categoryRepository.findAll())
                .message(categoryRepository.count() > 0 ? categoryRepository.count() + " categories found" : "No categories found")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //To DO use Format dateTime from DateUtil package
                //.timeStamp(LocalDateTime.now());
                .build();
    }
}
