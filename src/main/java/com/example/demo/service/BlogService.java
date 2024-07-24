package com.example.demo.service;

import com.example.demo.repo.AuthUserRepository;
import com.example.demo.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

}
