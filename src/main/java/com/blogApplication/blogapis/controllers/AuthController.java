package com.blogApplication.blogapis.controllers;

import com.blogApplication.blogapis.exception.ApiException;
import com.blogApplication.blogapis.payloads.JwtAuthRequest;
import com.blogApplication.blogapis.payloads.JwtAuthResponse;
import com.blogApplication.blogapis.payloads.UserDto;
import com.blogApplication.blogapis.security.JWTTokenHelper;
import com.blogApplication.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest){
             this.authenticate(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassword());


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        String token= this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

       try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException e){
           System.out.println("Invalid credentials");
           throw new ApiException("Invalid username or password");
       }

    }

    //Register new user
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto registerUser=this.userService.registerNewUser(userDto);

        return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
    }
}
