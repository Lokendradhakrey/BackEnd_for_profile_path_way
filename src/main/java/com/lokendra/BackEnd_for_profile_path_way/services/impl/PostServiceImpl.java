package com.lokendra.BackEnd_for_profile_path_way.services.impl;

import com.lokendra.BackEnd_for_profile_path_way.entities.Post;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import com.lokendra.BackEnd_for_profile_path_way.repositories.PostRepo;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;
    @Value("${project.file}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public PostDto createPost(PostDto postDto, MultipartFile file) throws IOException {
        String uploadedFilename = fileService.uploadFile(path, file);
        postDto.setFile(uploadedFilename);
        Post post = this.modelMapper.map(postDto, Post.class);
        Post savedPost = this.postRepo.save(post);
        String fileUrl = baseUrl+"/profile-path-way/v1/file/"+uploadedFilename;
        return new PostDto(
                savedPost.getId(),
                savedPost.getContent(),
                savedPost.getFile(),
                fileUrl
        );
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        String fileUrl = baseUrl+"/profile-path-way/v1/file/"+post.getFile();
        return new PostDto(
                post.getId(),
                post.getContent(),
                post.getFile(),
                fileUrl
        );
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post:posts){
            String fileUrl = baseUrl + "/profile-path-way/v1/file/" + post.getFile();
            PostDto postDto = new PostDto(
                    post.getId(),
                    post.getContent(),
                    post.getFile(),
                    fileUrl
            );
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    public void deletePost(Integer postId) throws IOException {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        Files.deleteIfExists(Paths.get(path + File.separator + post.getFile()));
        this.postRepo.delete(post);
    }
}
