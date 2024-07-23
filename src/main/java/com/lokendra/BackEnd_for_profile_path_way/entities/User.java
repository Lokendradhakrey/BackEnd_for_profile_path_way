package com.lokendra.BackEnd_for_profile_path_way.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String fullName;
    private String username;
    private String email;
    private String password;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Post> posts = new ArrayList<>();
}
