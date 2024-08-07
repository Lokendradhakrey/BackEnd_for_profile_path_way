package com.lokendra.BackEnd_for_profile_path_way.services.impl;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.entities.Comment;
import com.lokendra.BackEnd_for_profile_path_way.entities.Post;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;
import com.lokendra.BackEnd_for_profile_path_way.repositories.CommentRepo;
import com.lokendra.BackEnd_for_profile_path_way.repositories.PostRepo;
import com.lokendra.BackEnd_for_profile_path_way.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) throws ResourceNotFoundExceptionHandle {
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("Post", "id", postId));
        comment.setPost(post);
        comment.setDate(new Date());
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) throws ResourceNotFoundExceptionHandle {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("Comment", "id", commentId));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) throws ResourceNotFoundExceptionHandle {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("Comment", "id", commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto getComment(Integer commentId) throws ResourceNotFoundExceptionHandle {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("Comment", "id", commentId));
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepo.findAll();
        return comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).toList();
    }
}
