package com.example.demo.testing;

import com.example.demo.testing.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
