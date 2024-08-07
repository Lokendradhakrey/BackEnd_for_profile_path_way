package com.lokendra.BackEnd_for_profile_path_way.services.impl;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.UserDto;
import com.lokendra.BackEnd_for_profile_path_way.repositories.UserRepo;
import com.lokendra.BackEnd_for_profile_path_way.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) throws ResourceNotFoundExceptionHandle {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("User", "id", userId));
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundExceptionHandle {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("User", "id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUser(Integer userId) throws ResourceNotFoundExceptionHandle {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptionHandle("User", "id", userId));

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Optional<UserDto> getByUsername(String username) {
        Optional<User> user = this.userRepo.findByUsername(username);
        List<UserDto> allUsers = getAllUsers();
        for (UserDto userDto : allUsers) {
            if (userDto.getUsername().equals(username)) {
                return Optional.of(userDto);
            }
        }
        return Optional.empty();
    }
}
