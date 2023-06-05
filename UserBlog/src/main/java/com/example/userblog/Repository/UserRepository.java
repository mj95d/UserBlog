package com.example.userblog.Repository;

import com.example.userblog.Model.Blog;
import com.example.userblog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<Blog> findBlogsByUser(User user);

    org.springframework.security.core.userdetails.User findUserByUsername(String username);
}