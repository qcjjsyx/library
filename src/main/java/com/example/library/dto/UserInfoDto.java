package com.example.library.dto;


import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String username;
    private String nickName;
    private String address;
    private String phone;
    private String email;
    private String role;
}
