package com.example.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public IPage<UserInfoDto> searchUser(Integer pageNum, Integer pageSize, String name, String phone, String email) {
        if(pageNum==null ||pageNum<1){
            pageNum=1;
        }
        if(pageSize==null ||pageSize<1){
            pageSize=10;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getUpdatedAt);
        if(StringUtils.hasText(name)){
            queryWrapper.like(User::getNickName, name);
        }

        if(StringUtils.hasText(phone)){
            queryWrapper.like(User::getPhone, phone);
        }
        if(StringUtils.hasText(email)){
            queryWrapper.like(User::getEmail, email);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        if(userPage==null){
            return null;
        }
        return userPage.convert(this::toUserInfoDto);
    }

    @Override
    public void updateUser(Long id, UserInfoDto userInfoDto) {
        User user = toUser(id,userInfoDto);
        userMapper.updateById(user);
    }

    private User toUser(Long id,UserInfoDto userInfoDto) {
        User user = new User();
        user.setId(id);
        user.setUsername(userInfoDto.getUsername());
        user.setEmail(userInfoDto.getEmail());
        user.setPhone(userInfoDto.getPhone());
        user.setNickName(userInfoDto.getNickName());
        user.setRole(userInfoDto.getRole());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
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
