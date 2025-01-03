package com.bharti.blog_app_api.service.imp;

import com.bharti.blog_app_api.entity.Category;
import com.bharti.blog_app_api.entity.Post;
import com.bharti.blog_app_api.entity.User;
import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.payload.PostDto;
import com.bharti.blog_app_api.payload.PostResponse;
import com.bharti.blog_app_api.repository.CategoryRepo;
import com.bharti.blog_app_api.repository.PostRepo;
import com.bharti.blog_app_api.repository.UserRepo;
import com.bharti.blog_app_api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;


      //  Create Post

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User_id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category_id", categoryId));

        Post post = dtoToPost(postDto);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);

        return postToDto(newPost);
    }


    //   Update Post

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        if(postDto.getPostTitle() != null) {
            post.setPostTitle(postDto.getPostTitle());
        }

        if(postDto.getPostContent() != null) {
            post.setPostContent(postDto.getPostContent());
        }

        if(postDto.getImageName() != null) {
            post.setImageName(postDto.getImageName());
        }

        Post updatedPost = postRepo.save(post);
        return postToDto(updatedPost);
    }


    // Delete Post

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        postRepo.delete(post);
    }


    // Get Post by id

    @Override
    public PostDto getPost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));
        return postToDto(post);
    }


    // Get All Post

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDirection) {
        // Defining sorting direction
        Sort sort = sortDirection.equalsIgnoreCase("ascending")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Finding all post in pagination format
        Page<Post> page = this.postRepo.findAll(pageable);

        List<Post> posts = page.getContent();
        List<PostDto> postDtos = posts.stream().map(post->postToDto(post)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());
        return postResponse;
    }


    //Get  All Post By Category

    @Override
    public PostResponse getAllPostByCategory(int categoryId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category_id", categoryId));

        // Defining sorting direction
        Sort sort = sortDirection.equalsIgnoreCase("ascending")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postPage = this.postRepo.findAllPostsByCategory(category, pageable);
        List<Post> postList = postPage.getContent();

        List<PostDto> postDtoList = postList.stream().map((post)->this.postToDto(post)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }


    // Get All Post By User

    @Override
    public PostResponse getAllPostByUser(int userId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user_id", userId));

        // Defining sorting with direction
        Sort sort = sortDirection.equalsIgnoreCase("ascending")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postPage = this.postRepo.findAllPostsByUser(user, pageable);

        List<Post> posts = postPage.getContent();

        List<PostDto> postDtoList = posts.stream().map(post->this.postToDto(post)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }


//     Get All Post By Search
    @Override
    public List<PostDto> getAllPostBySearch(String keyword) {

        List<Post> posts = this.postRepo.findByPostTitleContaining(keyword);

        return posts.stream().map(post->postToDto(post)).toList();
    }

    private Post dtoToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
    private PostDto postToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
