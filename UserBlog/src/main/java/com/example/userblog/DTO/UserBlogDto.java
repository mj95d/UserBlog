package com.example.userblog.DTO;

import com.example.userblog.Model.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserBlogDto {
    private String username;
    private Set<Blog> blogs;
}

