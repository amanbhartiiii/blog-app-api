package com.bharti.blog_app_api.controller;

import com.bharti.blog_app_api.entity.Comment;
import com.bharti.blog_app_api.payload.ApiResponse;
import com.bharti.blog_app_api.payload.CommentDto;
import com.bharti.blog_app_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    // new Comment

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId, @PathVariable int userId) {
//        System.out.println(commentDto);
//        System.out.println(postId);
//        System.out.println(userId);
        CommentDto newComment = commentService.addComment(commentDto, postId, userId);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }


    // Update comment api
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable int commentId) {
        CommentDto updatedComment = commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }


    // delete comment api
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        ApiResponse response = new ApiResponse("Comment deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get comment
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable int commentId) {
        CommentDto commentDto = commentService.getComment(commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }


    // Get All Comments Api
    @GetMapping("/")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Get All Comments of User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfUser(@PathVariable int userId) {
        List<CommentDto> comments = commentService.getCommentsByUser(userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Get All Comments of Post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfPost(@PathVariable int postId) {
        List<CommentDto> comments = commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
