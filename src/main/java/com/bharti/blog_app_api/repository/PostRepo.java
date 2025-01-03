package com.bharti.blog_app_api.repository;

import com.bharti.blog_app_api.entity.Category;
import com.bharti.blog_app_api.entity.Post;
import com.bharti.blog_app_api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    Page<Post> findAllPostsByUser(User user, Pageable pageable);
    Page<Post> findAllPostsByCategory(Category category, Pageable pageable);

    // This method will generate a query like: SELECT * FROM Post WHERE title LIKE %title%;
    //It matches all records where the title contains the given string.
    List<Post> findByPostTitleContaining(String title);

}
