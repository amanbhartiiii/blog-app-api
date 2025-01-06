package com.bharti.blog_app_api.service.imp;

import com.bharti.blog_app_api.entity.Comment;
import com.bharti.blog_app_api.entity.Post;
import com.bharti.blog_app_api.entity.User;
import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.payload.CommentDto;
import com.bharti.blog_app_api.repository.CommentRepo;
import com.bharti.blog_app_api.repository.PostRepo;
import com.bharti.blog_app_api.repository.UserRepo;
import com.bharti.blog_app_api.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;


     // Add new Comment

    @Override
    public CommentDto addComment(CommentDto commentDto, int postId, int userId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));


        Comment comment = modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = commentRepo.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }


    // Update comment
    @Override
    public CommentDto updateComment(CommentDto commentDto, int commentId /*, int postId, int userId */ ) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        comment.setComment(commentDto.getComment());
        Comment updatedComment = commentRepo.save(comment);
        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        commentRepo.delete(comment);
    }

    @Override
    public CommentDto getComment(int commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return commentDtos;
    }

    @Override
    public List<CommentDto> getCommentsByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        List<Comment> comments = commentRepo.findAllCommentsByUser(user);
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();

        return commentDtos;
    }

    @Override
    public List<CommentDto> getCommentsByPost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepo.findAllCommentsByPost(post);
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return commentDtos;
    }
}
