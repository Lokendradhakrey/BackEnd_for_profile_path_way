package com.lokendra.BackEnd_for_profile_path_way.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import com.lokendra.BackEnd_for_profile_path_way.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile-path-way/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/create-post")
    public ResponseEntity<PostDto> createPost(@RequestPart MultipartFile file, @RequestPart String postDto, @PathVariable Integer userId) throws IOException, ResourceNotFoundExceptionHandle {
        PostDto dto = convertToPostDto(postDto);
        PostDto savedPostDto = this.postService.createPost(dto, file, userId);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }

    private PostDto convertToPostDto(String postDtoObj) throws JsonProcessingException {
        PostDto postDto = new PostDto();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(postDtoObj, PostDto.class);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) throws ResourceNotFoundExceptionHandle {
        PostDto postDto = this.postService.getPost(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<PostDto>> getPost(){
        List<PostDto> postDtos = this.postService.getAllPosts();
        return ResponseEntity.ok(postDtos);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) throws IOException, ResourceNotFoundExceptionHandle {
        this.postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully with id: "+postId);
    }
}
