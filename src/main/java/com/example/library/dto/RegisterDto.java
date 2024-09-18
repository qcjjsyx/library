package com.example.library.dto;


import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String nickName;
}
