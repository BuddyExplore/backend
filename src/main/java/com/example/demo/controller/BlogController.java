package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repo.BlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/travel/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final ResponseDTO responseDTO;

    @GetMapping(value = "/allBlog")
    public ResponseEntity<ResponseDTO> getAllBlogs() {
        try {

            List<Blog> blogs = blogService.getAllBlogs();

            if(!blogs.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all blogs");
                responseDTO.setContent(blogs);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No blogs found");
                responseDTO.setContent(null);
            }
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getBlogById(@PathVariable long id){
        try{
            Blog blog = blogService.getBlogById(id);
            if(blog != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Sending blog");
                responseDTO.setContent(blog);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No blog found");
                responseDTO.setContent(null);
            }
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createBlog")
    public ResponseEntity<ResponseDTO> createBlog(@RequestBody Blog blog){
        if(blog.getTitle() == null || blog.getContent() == null){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Error in the create blog request");
            responseDTO.setContent(null);
        }
        else {

            try {
                blog.setPosted_date((new Date(System.currentTimeMillis())).toString());
                blogService.addNewBlog(blog);

                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Created blog");
                responseDTO.setContent(null);
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Failed creation blog");
                responseDTO.setContent(null);
            }
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateBlog(@RequestBody Blog blog){
        if(blog.getId() < 1 ){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Missing blog id");
            responseDTO.setContent(null);
        }
        Blog currentBlog = blogService.getBlogById(blog.getId());

        if(blog.getTitle() == null) {
            blog.setTitle(currentBlog.getTitle());
        }
        if(blog.getContent() == null){
            blog.setContent(currentBlog.getContent());
        }

        try{
            blogService.updateBlog(blog);

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Updated blog");
            responseDTO.setContent(null);
        }
        catch (Exception e){
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Failed updating blog");
            responseDTO.setContent(e);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBlog(@PathVariable("id") long id){
        if(id <1){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Missing blog id");
            responseDTO.setContent(null);
        }
        else {
            try {

                Blog blog = blogService.getBlogById(id);
                if(blog == null){
                    responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
                    responseDTO.setMessage("Missing blog");
                    responseDTO.setContent(null);
                }
                else {
                    blogService.deleteBlog(id);
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Deletion Sucessfull");
                    responseDTO.setContent(null);
                }
            }
            catch (Exception e){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Failed delete blog");
                responseDTO.setContent(e);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }



}
