package com.bharti.blog_app_api.controller;

import com.bharti.blog_app_api.payload.ApiResponse;
import com.bharti.blog_app_api.payload.PostDto;
import com.bharti.blog_app_api.payload.PostResponse;
import com.bharti.blog_app_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    // Create post api
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {
        PostDto newPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    // Update post api
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Delete post api
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        ApiResponse response = new ApiResponse("Post deleted successfully with id "+postId, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get Post
    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
        PostDto postDto = postService.getPost(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // Get All Post api
    @GetMapping("/")
    public ResponseEntity<PostResponse> getPostsByPage(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                       @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
                                                       @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection){
        PostResponse posts = this.postService.getAllPost(pageNo, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get all post by User api
    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable int userId, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                       @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
                                                       @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {
        PostResponse postList = postService.getAllPostByUser(userId, pageNo, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // Get All posts by Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable int categoryId, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
                                                           @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {
        PostResponse postList = postService.getAllPostByCategory(categoryId, pageNo, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

//    // get All Post by Search
//    @GetMapping("/search")
//    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam("keyword")String keyword) {
//        List<PostDto> posts = this.postService.getAllPostBySearch(keyword);
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }
}
