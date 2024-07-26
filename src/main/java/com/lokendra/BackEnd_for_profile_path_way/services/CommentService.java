package com.lokendra.BackEnd_for_profile_path_way.services;

import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto, Integer commentId);
    void deleteComment(Integer commentId);
    CommentDto getComment(Integer commentId);
    List<CommentDto> getAllComments();
}
