package com.lokendra.BackEnd_for_profile_path_way.services;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId) throws ResourceNotFoundExceptionHandle;
    void deleteUser(Integer userId) throws ResourceNotFoundExceptionHandle;
    UserDto getUser(Integer userId) throws ResourceNotFoundExceptionHandle;
    List<UserDto> getAllUsers();
    Optional<UserDto> getByUsername(String username);
}
