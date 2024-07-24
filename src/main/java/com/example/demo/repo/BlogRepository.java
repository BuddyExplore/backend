package com.example.demo.repo;

import com.example.demo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findById(Long id);

    Blog getBlogById(long id);
}
