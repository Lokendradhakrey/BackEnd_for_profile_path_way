package com.lokendra.BackEnd_for_profile_path_way.services.impl;

import com.lokendra.BackEnd_for_profile_path_way.entities.Comment;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;
import com.lokendra.BackEnd_for_profile_path_way.repositories.CommentRepo;
import com.lokendra.BackEnd_for_profile_path_way.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto getComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepo.findAll();
        return comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).toList();
    }
}
