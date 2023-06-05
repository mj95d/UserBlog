package com.example.userblog.Service;

import com.example.userblog.ApiExpation.ApiException;
import com.example.userblog.Model.Blog;
import com.example.userblog.Model.User;
import com.example.userblog.Repository.BlogRepository;
import com.example.userblog.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs(Integer userId){
        return blogRepository.findByUser_Id(userId);
    }

    public void add(User userId, Blog blog){
        blog.setUser(userId);
        blogRepository.save(blog);
    }

    public void update(int id, int userId, Blog blog){
        Blog findBlog = blogRepository.findById(id).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        findBlog.setTitle(blog.getTitle());
        findBlog.setId(blog.getId());
        blogRepository.save(findBlog);
    }

    public void delete(int id, int userId){
        Blog findBlog = blogRepository.findById(id).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        blogRepository.delete(findBlog);
    }

    public Blog getBlogById(Integer userId, int blogId){
        Blog findBlog = blogRepository.findById(blogId).orElseThrow(() -> new ApiException("blog is not found"));

        if(!findBlog.getUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findBlog;
    }

    public Blog getBlogByTitle(Integer userId, String blogTitle){
        Blog findBlog = blogRepository.findBlogByTitle(blogTitle);

        if(findBlog == null)
            throw new ApiException("title not found");

        if(!findBlog.getUser().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findBlog;
    }

    public List<Blog> getBlogsByTitle(String title) {
        return blogRepository.findByTitleContaining(title);
    }
}