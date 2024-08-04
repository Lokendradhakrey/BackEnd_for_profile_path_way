package com.lokendra.BackEnd_for_profile_path_way.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;
    @Column(name = "content")
    @Size(min = 3, max = 300, message = "Content must be between 3 and 50 characters")
    private String content;
    @Column(name = "file", nullable = false)
    @NotNull(message = "File is required")
    private String file;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}