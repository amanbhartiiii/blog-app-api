package com.bharti.blog_app_api.service;

import com.bharti.blog_app_api.payload.CommentDto;

import java.util.List;

public interface CommentService {

    // create
    CommentDto addComment(CommentDto commentDto, int postId, int userId);

    //Update
    CommentDto updateComment(CommentDto commentDto, int commentId /*, int postId, int userId*/ );

    // delete
    void deleteComment(int commentId);

    // Get comment by id
    CommentDto getComment(int commentId);

    // Get All Comment
    List<CommentDto> getAllComments();

    // Get All Comments of User
    List<CommentDto> getCommentsByUser(int userId);


    // Get All Comments of Post
    List<CommentDto> getCommentsByPost(int postId);
}
