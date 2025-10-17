package com.blogApplication.blogapis.security;

import com.blogApplication.blogapis.entities.User;
import com.blogApplication.blogapis.exception.ResourceNotFoundException;
import com.blogApplication.blogapis.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user=this.userRepo.findByName(username).orElseThrow(()->new ResourceNotFoundException("User not found","username", username));
        return user;
    }
}
