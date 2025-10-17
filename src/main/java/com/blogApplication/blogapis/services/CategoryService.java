package com.blogApplication.blogapis.services;


import com.blogApplication.blogapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //post
    CategoryDto createCategory(CategoryDto categoryDto);

    //put
    CategoryDto updateCategory(int id, CategoryDto categoryDto);

    //delete
    void deleteCategory(int id);

    //get
    CategoryDto getCategoryById(int id);

    //get all
    List<CategoryDto> getAllCategories();

}
