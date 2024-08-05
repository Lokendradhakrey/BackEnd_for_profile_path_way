package com.lokendra.BackEnd_for_profile_path_way.services.impl;

import com.lokendra.BackEnd_for_profile_path_way.entities.Post;
import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import com.lokendra.BackEnd_for_profile_path_way.repositories.PostRepo;
import com.lokendra.BackEnd_for_profile_path_way.repositories.UserRepo;
import com.lokendra.BackEnd_for_profile_path_way.services.FileService;
import com.lokendra.BackEnd_for_profile_path_way.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final FileService fileService;

    public PostServiceImpl(PostRepo postRepo, UserRepo userRepo, ModelMapper modelMapper, FileService fileService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Value("${project.file}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public PostDto createPost(PostDto postDto, MultipartFile file, Integer userId) throws IOException {
        String uploadedFilename = fileService.uploadFile(path, file);
        postDto.setFile(uploadedFilename);
        Post post = this.modelMapper.map(postDto, Post.class);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        post.setUser(user);
        post.setDate(new Date());
        Post savedPost = this.postRepo.save(post);
        String fileUrl = baseUrl + "/profile-path-way/v1/post/" + uploadedFilename;
        PostDto response = this.modelMapper.map(savedPost, PostDto.class);
        response.setFileUrl(fileUrl);
        return response;
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        String fileUrl = baseUrl + "/profile-path-way/v1/post/" + post.getFile();
        PostDto response = this.modelMapper.map(post, PostDto.class);
        response.setFileUrl(fileUrl);
        response.setDate(new Date());
        return response;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map(post -> {
            String fileUrl = baseUrl + "/profile-path-way/v1/post/" + post.getFile();
            PostDto postDto = this.modelMapper.map(post, PostDto.class);
            postDto.setFileUrl(fileUrl);
            postDto.setDate(new Date());
            return postDto;
        }).toList();
    }

    @Override
    public void deletePost(Integer postId) throws IOException {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        Files.deleteIfExists(Paths.get(path + File.separator + post.getFile()));
        this.postRepo.delete(post);
    }
}
