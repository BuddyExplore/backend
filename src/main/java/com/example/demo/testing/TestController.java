package com.example.demo.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestController {

    private final TestService testService;

    @GetMapping("/hello")
    public ResponseEntity<?> sayHello() {
        return ResponseEntity.ok("Hello from backend !");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFormData(
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("coverImage") MultipartFile file
            ) {

        System.out.println("Title: " + name);
        System.out.println("Description: " + address);

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            // Define a base directory for uploads
            String baseDir = "uploads/shop";

            // Ensure the upload directory exists
            Path uploadPath = Paths.get(baseDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique file name to avoid conflicts
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

            // Define the full file path
            Path filePath = uploadPath.resolve(uniqueFilename);

            // Save the file
            Files.copy(file.getInputStream(), filePath);

            System.out.println("File saved to: " + filePath.toString());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed");
        }

        return ResponseEntity.ok("Successfully Created !");
    }


}
