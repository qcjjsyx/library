package com.example.library.service.impl;

import com.example.library.common.LibraryException;
import com.example.library.domain.User;
import com.example.library.dto.LoginRequesetDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterDto;
import com.example.library.dto.UserInfoDto;
import com.example.library.mapper.UserMapper;
import com.example.library.service.UserService;
import com.example.library.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;


    @Override
    public void register(RegisterDto registerDto) {
        User originUser = userMapper.findOneByUsername(registerDto.getUsername());
        if (originUser != null) {
            throw new LibraryException(400,"用户已经存在");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setAddress(registerDto.getAddress());
        user.setRole(registerDto.getRole());
        user.setNickName(registerDto.getNickName());
        userMapper.insert(user);
    }

    @Override
    public LoginResponseDto login(LoginRequesetDto loginRequesetDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequesetDto.getUsername(), loginRequesetDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtUtil.generateToken(loginRequesetDto.getUsername()));
        return loginResponseDto;
    }

    @Override
    public UserInfoDto getUserByUserName(String username) {
        User user = userMapper.findOneByUsername(username);
        if(user == null) {
            return null;
        }
        return toUserInfoDto(user);
    }

    private UserInfoDto toUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setNickName(user.getNickName());
        userInfoDto.setAddress(user.getAddress());
        userInfoDto.setPhone(user.getPhone());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setRole(user.getRole());
        return userInfoDto;
    }
}
