package com.example.demo.testing;

import com.example.demo.testing.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public String uploadForm() {
        return "";
    }

}
