package com.lokendra.BackEnd_for_profile_path_way.repositories;

import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
