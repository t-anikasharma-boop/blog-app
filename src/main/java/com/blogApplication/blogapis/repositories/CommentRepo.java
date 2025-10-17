package com.blogApplication.blogapis.repositories;

import com.blogApplication.blogapis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
