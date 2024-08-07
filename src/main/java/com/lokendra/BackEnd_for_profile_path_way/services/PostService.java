package com.lokendra.BackEnd_for_profile_path_way.services;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, MultipartFile file, Integer userId) throws IOException, ResourceNotFoundExceptionHandle;
    PostDto getPost(Integer postId) throws ResourceNotFoundExceptionHandle;
    List<PostDto> getAllPosts();
    void deletePost(Integer postId) throws IOException, ResourceNotFoundExceptionHandle;
}
