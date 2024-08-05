package com.lokendra.BackEnd_for_profile_path_way.repositories;

import com.lokendra.BackEnd_for_profile_path_way.entities.Post;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.PostDto;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
}
