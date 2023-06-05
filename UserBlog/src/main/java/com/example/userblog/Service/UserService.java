package com.example.userblog.Service;

import com.example.userblog.ApiExpation.ApiException;
import com.example.userblog.DTO.UserBlogDto;
import com.example.userblog.Model.Blog;
import com.example.userblog.Model.User;
import com.example.userblog.Repository.BlogRepository;
import com.example.userblog.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private UserRepository userRepository;

    public UserBlogDto getAll(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        UserBlogDto userDto = new UserBlogDto(User.getUsername(), User.getBlogs());
        userDto.setUsername(User.getUsername());
        userDto.setBlogs(User.getBlogs());

        return userDto;
    }

    public void add(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }

    public void update(int userId, User user){
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        findUser.setPassword(hash);
        userRepository.save(findUser);
    }

    public void delete(User userId){
        User findUser = userRepository.findById(userId.getId())
                .orElseThrow(() -> new ApiException("User not found"));

        userRepository.delete(findUser);
    }
}