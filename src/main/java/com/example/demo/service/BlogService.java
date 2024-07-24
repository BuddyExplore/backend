package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.repo.AuthUserRepository;
import com.example.demo.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs;
    }

    public Blog getBlogById(long id) {
        return blogRepository.getBlogById(id);
    }

    public void addNewBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void updateBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }
}
