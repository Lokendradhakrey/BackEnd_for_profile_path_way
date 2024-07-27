package com.lokendra.BackEnd_for_profile_path_way.payloads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;
    private String content;
    private String file;
    private String fileUrl;
    private Date date;
//    private User user;
//    private List<Comment> comments = new ArrayList<>();
}