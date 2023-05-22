package com.announceMe.controller;

import com.announceMe.entity.Category;
import com.announceMe.entity.HttpResponse;
import com.announceMe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/all")
    public ResponseEntity<HttpResponse<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
        //return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(path = "/{idCategory}")
    public ResponseEntity<HttpResponse<Category>> getCategoryById(@PathVariable(value = "idCategory") Long idCategory) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(idCategory));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<HttpResponse<Category>> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.addNewCategory(category));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<HttpResponse<Category>> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping(path = "/delete/{idCategory}")
    public ResponseEntity<HttpResponse<Category>> deleteCategory(@PathVariable(value = "idCategory") Long idCategory) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(idCategory));
    }

    //TODO: Add request param for error handling

}
