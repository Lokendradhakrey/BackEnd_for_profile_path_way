package com.lokendra.BackEnd_for_profile_path_way.payloads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;
    private String content;
    private String comment;
    private String file;
//    private User user;
//    private List<Comment> comments = new ArrayList<>();
}