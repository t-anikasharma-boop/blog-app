package com.blogApplication.blogapis.payloads;

import com.blogApplication.blogapis.entities.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty
    private String about;

    private Set<CommentDto> commentList=new HashSet<>();

    private Set<RoleDto> roles=new HashSet<>();

}
