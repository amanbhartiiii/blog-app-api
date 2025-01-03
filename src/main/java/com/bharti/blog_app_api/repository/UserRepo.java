package com.bharti.blog_app_api.repository;

import com.bharti.blog_app_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
