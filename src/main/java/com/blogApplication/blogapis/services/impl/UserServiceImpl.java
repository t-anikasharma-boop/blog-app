package com.blogApplication.blogapis.services.impl;

import com.blogApplication.blogapis.config.AppConstants;
import com.blogApplication.blogapis.entities.Role;
import com.blogApplication.blogapis.entities.User;
import com.blogApplication.blogapis.exception.ResourceNotFoundException;
import com.blogApplication.blogapis.payloads.CommentDto;
import com.blogApplication.blogapis.payloads.UserDto;
import com.blogApplication.blogapis.repositories.RoleRepo;
import com.blogApplication.blogapis.repositories.UserRepo;
import com.blogApplication.blogapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //role
        Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User saved=this.userRepo.save(user);

        return this.modelMapper.map(saved,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
           // Convert UserDto to User entity
            User user = dtoToEntity(userDto);

            // Save user entity to database
            User savedUser = userRepo.save(user);

            // Convert User entity to UserDto
            UserDto savedUserDto = EntityTodto(savedUser);

            return savedUserDto;

    }

    @Override
    public UserDto getUserById(int id) {
         User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

         UserDto userDto=this.modelMapper.map(user,UserDto.class);

         return userDto;
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatesUser=this.userRepo.save(user);

        return this.EntityTodto(updatesUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=this.userRepo.findAll();
        List<UserDto> userDtoList=userList.stream().map(user->EntityTodto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(int id) {
       User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
       this.userRepo.delete(user);
    }

    private User dtoToEntity(UserDto userDto) {
        // Convert UserDto to User entity
        User user = this.modelMapper.map(userDto, User.class);

//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto EntityTodto(User user) {
        // Convert User entity to UserDto
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
