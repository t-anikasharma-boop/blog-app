package com.blogApplication.blogapis.controllers;

import com.blogApplication.blogapis.payloads.ApiResponse;
import com.blogApplication.blogapis.payloads.CategoryDto;
import com.blogApplication.blogapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    //post
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Successfully Deleted",true),HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategroy(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int id){
        CategoryDto updatedCategory=this.categoryService.updateCategory(id,categoryDto);
        return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
    }

    //getall
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    //getbyid
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
        return  ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }
}
