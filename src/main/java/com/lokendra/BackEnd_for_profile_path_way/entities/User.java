package com.lokendra.BackEnd_for_profile_path_way.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "full_name", nullable = false)
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String fullName;
    @Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotNull(message = "Email is required")
    private String email;
    @Column(name = "password", nullable = false)
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Post> posts = new ArrayList<>();
}
