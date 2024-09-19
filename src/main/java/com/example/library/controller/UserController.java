package com.example.library.controller;


import com.example.library.common.PlainResult;
import com.example.library.dto.UserInfoDto;
import com.example.library.service.UserService;
import com.example.library.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private JwtUtil jwtUtil;

    @GetMapping
    public PlainResult<UserInfoDto> getUserInfo(@RequestParam String token){
        String username = jwtUtil.extractUsername(token);
        UserInfoDto user= userService.getUserByUserName(username);
        return PlainResult.success(user);
    }
}
