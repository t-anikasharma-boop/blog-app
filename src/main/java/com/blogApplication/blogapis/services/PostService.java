package com.blogApplication.blogapis.services;

import com.blogApplication.blogapis.entities.Post;
import com.blogApplication.blogapis.payloads.PostDto;
import com.blogApplication.blogapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto,int user_id,int category_id);

    //update post
    PostDto updatePost(int id,PostDto postDto);

    //delete post
    void deletePost(int id);

    //get all posts
    PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir);

    //get post by id
    PostDto getPostById(int id);

    //get post by user
    List<PostDto> getPostByUser(int user_id);

    //get post By category
    List<PostDto> getPostByCategory(int category_id);

    //searchPost
    List<PostDto> searchPost(String keyword);
}
