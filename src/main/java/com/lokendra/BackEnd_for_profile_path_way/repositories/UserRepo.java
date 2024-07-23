package com.lokendra.BackEnd_for_profile_path_way.repositories;

import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
