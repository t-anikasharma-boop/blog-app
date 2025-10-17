package com.blogApplication.blogapis.controllers;

import com.blogApplication.blogapis.config.AppConstants;
import com.blogApplication.blogapis.payloads.ApiResponse;
import com.blogApplication.blogapis.payloads.PostDto;
import com.blogApplication.blogapis.payloads.PostResponse;
import com.blogApplication.blogapis.services.FileService;
import com.blogApplication.blogapis.services.PostService;
import com.blogApplication.blogapis.services.impl.RedisService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;


    private String path="images/";


    //create post
    @PostMapping("users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable int userId, @PathVariable int categoryId){

         PostDto createPost=this.postService.createPost(postdto, userId, categoryId);

         return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get post by category
    @GetMapping("category/{category_Id}/posts")
    public ResponseEntity<List<PostDto>> getByPostByCategoryId(@PathVariable int category_Id){
        List<PostDto> postDtoList=this.postService.getPostByCategory(category_Id);

        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //get post by User
    @GetMapping("user/{user_Id}/posts")
    public ResponseEntity<List<PostDto>> getByPostByUserId(@PathVariable int user_Id){
        List<PostDto> postDtoList=this.postService.getPostByUser(user_Id);

        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
        PostDto postDto=this.postService.getPostById(postId);

        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("posts/")
    public ResponseEntity<PostResponse> getALLPosts(@RequestParam(value = "pageNumber" ,defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
                                                    @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                    @RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false)String sortDir){
        PostResponse allPosts=this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }

    //update post
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId,@RequestBody PostDto postDto){
        PostDto updatedPost=this.postService.updatePost(postId,postDto);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //search
    @GetMapping("posts/search/{keyword}")  //search by title
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
        List<PostDto> postDtoList=this.postService.searchPost(keyword);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //postImage upload
    @PostMapping("posts/image/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,@PathVariable int postId) throws IOException {
            PostDto postDto=this.postService.getPostById(postId);
            String fileName=this.fileService.uploadImage(path,image);
            postDto.setPostImage(fileName);
            PostDto updatedPost=this.postService.updatePost(postId,postDto);

            return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //method to serve image
    @GetMapping(value="posts/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
