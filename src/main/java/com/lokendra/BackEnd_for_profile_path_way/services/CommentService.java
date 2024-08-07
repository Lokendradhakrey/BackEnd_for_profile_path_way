package com.lokendra.BackEnd_for_profile_path_way.services;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId) throws ResourceNotFoundExceptionHandle;
    CommentDto updateComment(CommentDto commentDto, Integer commentId) throws ResourceNotFoundExceptionHandle;
    void deleteComment(Integer commentId) throws ResourceNotFoundExceptionHandle;
    CommentDto getComment(Integer commentId) throws ResourceNotFoundExceptionHandle;
    List<CommentDto> getAllComments();
}
