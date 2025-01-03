package com.bharti.blog_app_api.service;

import com.bharti.blog_app_api.payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer id);
    void deleteUser(Integer userId);
    List<UserDto> getAllUsers();
}
