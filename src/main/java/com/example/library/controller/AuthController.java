package com.example.library.controller;


import com.example.library.common.PlainResult;
import com.example.library.dto.LoginRequesetDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterDto;
import com.example.library.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public PlainResult<String> register(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return PlainResult.success("注册成功");
    }

    @PostMapping("/login")
    public PlainResult<LoginResponseDto> login(@RequestBody LoginRequesetDto loginRequesetDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequesetDto);
        return PlainResult.success(loginResponseDto);
    }

}
