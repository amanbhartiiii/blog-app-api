package com.bharti.blog_app_api.repository;

import com.bharti.blog_app_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
