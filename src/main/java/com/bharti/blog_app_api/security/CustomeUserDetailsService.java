package com.bharti.blog_app_api.security;

import com.bharti.blog_app_api.entity.User;
import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // loading user from database by email
        User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User", "email",email));

        return user;
//                org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//                .roles(String.valueOf(user.getRoles()))
//                .build();
    }
}
