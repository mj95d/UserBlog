package com.example.userblog.Repository;

import com.example.userblog.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByUser_Id(String userId);

    Optional<Blog> findByIdAndUser_Id(Long id, Long userId);

    List<Blog> findByTitleContaining(String title);
}
