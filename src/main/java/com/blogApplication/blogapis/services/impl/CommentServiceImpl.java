package com.blogApplication.blogapis.services.impl;

import com.blogApplication.blogapis.entities.Comment;
import com.blogApplication.blogapis.entities.Post;
import com.blogApplication.blogapis.entities.User;
import com.blogApplication.blogapis.exception.ResourceNotFoundException;
import com.blogApplication.blogapis.payloads.CommentDto;
import com.blogApplication.blogapis.repositories.CommentRepo;
import com.blogApplication.blogapis.repositories.PostRepo;
import com.blogApplication.blogapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto,int postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        User user=post.getUser();

        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPostCommented(post);
        comment.setUserCommented(post.getUser());
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(int id) {
        Comment comment=this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
        this.commentRepo.delete(comment);
    }
}
