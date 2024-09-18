package com.example.library.service;

import com.example.library.dto.LoginRequesetDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterDto;

public interface UserService {
    void register(RegisterDto registerDto);

    LoginResponseDto login(LoginRequesetDto loginRequesetDto);

}
