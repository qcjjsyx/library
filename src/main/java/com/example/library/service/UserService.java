package com.example.library.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.library.dto.LoginRequesetDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterDto;
import com.example.library.dto.UserInfoDto;

import java.util.List;

public interface UserService {
    void register(RegisterDto registerDto);

    LoginResponseDto login(LoginRequesetDto loginRequesetDto);

    UserInfoDto getUserByUserName(String username);

    IPage<UserInfoDto> searchUser(Integer pageNum, Integer pageSize, String name, String phone, String email);

    void updateUser(Long id, UserInfoDto userInfoDto);

    void deleteUser(Long id);

    void deleteBatch(List<Long> ids);
}
