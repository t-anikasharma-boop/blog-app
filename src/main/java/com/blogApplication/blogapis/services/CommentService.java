package com.blogApplication.blogapis.services;

import com.blogApplication.blogapis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,int postId);

    void deleteComment(int id);
}
