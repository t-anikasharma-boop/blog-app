package com.blogApplication.blogapis.payloads;

import com.blogApplication.blogapis.entities.Category;
import com.blogApplication.blogapis.entities.Comment;
import com.blogApplication.blogapis.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int id; // 'id' field is added to the PostDto class
    private String postTitle;
    private String postContent;
    private String postImage;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> commentList=new ArrayList<>();
}
