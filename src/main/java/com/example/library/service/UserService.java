package com.example.library.service;

import com.example.library.dto.LoginRequesetDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterDto;
import com.example.library.dto.UserInfoDto;

public interface UserService {
    void register(RegisterDto registerDto);

    LoginResponseDto login(LoginRequesetDto loginRequesetDto);

    UserInfoDto getUserByUserName(String username);
}
