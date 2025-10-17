package com.blogApplication.blogapis.controllers;

import com.blogApplication.blogapis.payloads.ApiResponse;
import com.blogApplication.blogapis.payloads.UserDto;
import com.blogApplication.blogapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //POST- create user
    @PostMapping("/")
     public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
             UserDto createdUserDto=this.userService.createUser(userDto);
             return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
     }

     //Put- update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int id){
        UserDto updatedUser=this.userService.updateUser(id,userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    //ADMIN
    //Delete- delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User Successfully Deleted",true),HttpStatus.OK);
    }

    //Get- get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getuserById(@PathVariable int id){
        return  ResponseEntity.ok(this.userService.getUserById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }




}
