package com.example.demo.controller;

import com.example.demo.repo.BlogRepository;
import com.example.demo.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;
}
