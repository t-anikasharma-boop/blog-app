package com.blogApplication.blogapis.services;

import com.blogApplication.blogapis.entities.User;
import com.blogApplication.blogapis.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);
    UserDto getUserById(int id);
    UserDto updateUser(int id, UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteUser(int id);

}
