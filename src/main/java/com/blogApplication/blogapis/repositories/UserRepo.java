package com.blogApplication.blogapis.repositories;

import com.blogApplication.blogapis.entities.Post;
import com.blogApplication.blogapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

     Optional<User> findByName(String name);
}
