package com.blogApplication.blogapis.repositories;

import com.blogApplication.blogapis.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
