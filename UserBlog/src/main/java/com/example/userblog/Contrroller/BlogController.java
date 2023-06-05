package com.example.userblog.Contrroller;

import com.example.userblog.Model.Blog;
import com.example.userblog.Model.User;
import com.example.userblog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

        @GetMapping("/getAll")
        public ResponseEntity getAll(@AuthenticationPrincipal User userId) {
            return ResponseEntity.status(200).body(this.blogService.getAllBlogs(userId.getId()));
        }

        @PostMapping("/add")
        public ResponseEntity add(@AuthenticationPrincipal User userId, @Valid @RequestBody Blog blog) {
            this.blogService.add(userId, blog);
            return ResponseEntity.status(200).body("blog added");
        }

        @PutMapping("/update/{id}")
        public ResponseEntity update(@AuthenticationPrincipal User userId, @PathVariable int id, @Valid @RequestBody Blog blog) {
            this.blogService.update(id, userId.getId(), blog);
            return ResponseEntity.status(200).body("blog updated");
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity delete(@PathVariable int id, @AuthenticationPrincipal User userId) {
            this.blogService.delete(id, userId.getId());
            return ResponseEntity.status(200).body("blog deleted");
        }

        @GetMapping("/getById/{id}")
        public ResponseEntity getById(@PathVariable int id, @AuthenticationPrincipal User userId) {
            Blog blog = this.blogService.getBlogById(userId.getId(), id);
            return ResponseEntity.status(200).body(blog);
        }

        @GetMapping("/getByTitle/{title}")
        public ResponseEntity getById(@PathVariable String title, @AuthenticationPrincipal User userId) {
            Blog blog = this.blogService.getBlogByTitle(userId.getId(), title);
            return ResponseEntity.status(200).body(blog);
        }
    }
