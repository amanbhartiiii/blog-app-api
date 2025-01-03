package com.bharti.blog_app_api.service.imp;

import com.bharti.blog_app_api.entity.User;
import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.payload.UserDto;
import com.bharti.blog_app_api.repository.UserRepo;
import com.bharti.blog_app_api.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(" User ", " Id ", userId));

//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        userDto.setUserId(userId);

        User updatedUser = userRepo.save(dtoToUser(userDto));
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
            User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(" User "," Id ", userId));
            return userToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(" User "," Id ", userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users) {
            userDtos.add(this.userToDto(user));
        }
        return userDtos;
    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setUserId(user.getUserId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
