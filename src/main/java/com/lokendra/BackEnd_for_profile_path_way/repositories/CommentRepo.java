package com.lokendra.BackEnd_for_profile_path_way.repositories;

import com.lokendra.BackEnd_for_profile_path_way.entities.Comment;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
