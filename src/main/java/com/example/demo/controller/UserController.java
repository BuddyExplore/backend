package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.VarList;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    //@Autowired
    private final ResponseDTO responseDTO;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {

            List<User> users = userService.getAllUsers();

            List<UserDTO> userDTOList = new ArrayList<>();
            System.out.println("hi");

            for (User user: users) {
                System.out.println("user: " + user.getEmail());
                UserDTO userDTO = new UserDTO(user.getId(), user.getFirst_name(), user.getLast_name(), user.getMobile_no(), user.getEmail(), user.getRole().toString(),user.getEnabled());
                userDTOList.add(userDTO);
            }



            if(!users.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all users");
                responseDTO.setContent(userDTOList);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No users found");
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

    @GetMapping("/getUsersByRole/{role}")
    public ResponseEntity<ResponseDTO> getUsersByRole(@PathVariable Role role) {
        try {
            List<User> users = userService.getUsersByRole(role);

            if(!users.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all users by role");
                responseDTO.setContent(users);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No users found");
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


    @GetMapping("/{email}")
    public ResponseEntity<ResponseDTO> findByEmail(@PathVariable String email) {

        try {
            User user = userService.findByEmail(email);

            if(user != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("User found");
                responseDTO.setContent(user);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User not found");
                responseDTO.setContent(null);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Server Error");
            responseDTO.setContent(null);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("getUser/{userid}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable long userid) {

        try {
            User user = userService.findById(userid);

            if(user != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("User found");
                responseDTO.setContent(user);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User not found");
                responseDTO.setContent(null);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Server Error");
            responseDTO.setContent(null);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody User user) {
        System.out.println("use: "+user);
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userObj = userService.save(user);

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("User added successfully");
            responseDTO.setContent(userObj);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("User saving failed");
            responseDTO.setContent(null);

            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{email}")
    public ResponseEntity<ResponseDTO> updateUsers(@PathVariable String email, @RequestBody User user) {

        try {

            User savedUser = userService.updateUser(email, user);

            if(savedUser != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("User found");
                responseDTO.setContent(savedUser);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User not found");
                responseDTO.setContent(null);
            }

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("User saving failed");
            responseDTO.setContent(null);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String email) {
        try {
            User deletedUser = userService.deleteUser(email);

            if(deletedUser != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("User deleted successfully");
                responseDTO.setContent(deletedUser);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User not found");
                responseDTO.setContent(null);
            }

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("User deletion failed");
            responseDTO.setContent(null);
        }

        return ResponseEntity.ok(responseDTO);
    }

}

