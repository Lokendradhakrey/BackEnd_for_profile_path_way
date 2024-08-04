package com.lokendra.BackEnd_for_profile_path_way.payloads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private String password;
}
