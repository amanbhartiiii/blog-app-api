package com.bharti.blog_app_api.controller;

import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.payload.ApiResponse;
import com.bharti.blog_app_api.payload.UserDto;
import com.bharti.blog_app_api.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

        // No need of below line code because Global Exception Handler automatically handle exception
//        try {
//            UserDto user = userService.getUserById(userId);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        catch (ResourceNotFoundException e) {
//            log.error("ResourceNotFoundException {},  {}", e.getMessage(), Arrays.toString(e.getStackTrace()));
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable Integer userId) {
        UserDto updatedUser = userService.updateUser(user, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        ApiResponse response = new ApiResponse("User deleted successfully with id : "+userId, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
