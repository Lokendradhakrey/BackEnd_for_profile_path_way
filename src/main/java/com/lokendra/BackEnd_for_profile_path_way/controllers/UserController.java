package com.lokendra.BackEnd_for_profile_path_way.controllers;

import com.lokendra.BackEnd_for_profile_path_way.Exceptions.ResourceNotFoundExceptionHandle;
import com.lokendra.BackEnd_for_profile_path_way.payloads.dto.UserDto;
import com.lokendra.BackEnd_for_profile_path_way.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/profile-path-way/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) throws ResourceNotFoundExceptionHandle {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws ResourceNotFoundExceptionHandle {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully with id: " + userId, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) throws ResourceNotFoundExceptionHandle {
        UserDto user = this.userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<Optional<UserDto>> getByUsername(@RequestPart String username) {
        Optional<UserDto> user = this.userService.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getUser() {
        List<UserDto> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
