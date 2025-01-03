package com.bharti.blog_app_api.service;

import com.bharti.blog_app_api.payload.CategoryDto;
import com.bharti.blog_app_api.payload.PostDto;
import com.bharti.blog_app_api.payload.PostResponse;
import com.bharti.blog_app_api.payload.UserDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, int userId, int categoryId);
    PostDto updatePost(PostDto postDto, int postId);
    void deletePost(int postId);
    PostDto getPost(int postId);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDirection);

    // Get all posts by category
    PostResponse getAllPostByCategory(int categoryId, int pageNo, int pageSize, String sortBy, String sortDirection);

    // Get all posts by user
    PostResponse getAllPostByUser(int postId, int pageNo, int pageSize, String sortBy, String sortDirection);

    // Get posts by search keyWord
//    List<PostDto> getAllPostBySearch(String keyword);
}
