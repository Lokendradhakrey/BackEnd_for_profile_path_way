package com.lokendra.BackEnd_for_profile_path_way.repositories;

import com.lokendra.BackEnd_for_profile_path_way.entities.Comment;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
