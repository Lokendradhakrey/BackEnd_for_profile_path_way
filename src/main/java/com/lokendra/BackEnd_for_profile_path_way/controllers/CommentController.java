package com.lokendra.BackEnd_for_profile_path_way.controllers;

import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;
import com.lokendra.BackEnd_for_profile_path_way.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile-path-way/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/create-comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createdComment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable Integer commentId){
        CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully with id: "+commentId);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId){
        CommentDto comment = this.commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/all-comments")
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> comments = this.commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
}
