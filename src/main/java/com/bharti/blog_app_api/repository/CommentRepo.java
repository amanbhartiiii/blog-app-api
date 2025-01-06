package com.bharti.blog_app_api.repository;

import com.bharti.blog_app_api.entity.Comment;
import com.bharti.blog_app_api.entity.Post;
import com.bharti.blog_app_api.entity.User;
import com.bharti.blog_app_api.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllCommentsByPost(Post post);
    List<Comment> findAllCommentsByUser(User user);
}
