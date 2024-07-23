package com.lokendra.BackEnd_for_profile_path_way.services;

import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    void deleteUser(Integer userId);
    UserDto getUser(Integer userId);
    List<UserDto> getAllUsers();
}
