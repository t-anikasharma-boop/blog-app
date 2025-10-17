package com.blogApplication.blogapis.services.impl;

import com.blogApplication.blogapis.entities.Category;
import com.blogApplication.blogapis.exception.ResourceNotFoundException;
import com.blogApplication.blogapis.payloads.CategoryDto;
import com.blogApplication.blogapis.repositories.CategoryRepo;
import com.blogApplication.blogapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category savedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(int id, CategoryDto categoryDto) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int id) {
        this.categoryRepo.delete(this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id)));
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();

        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
