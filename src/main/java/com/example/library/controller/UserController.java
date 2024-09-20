package com.example.library.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.library.common.PlainResult;
import com.example.library.dto.UserInfoDto;
import com.example.library.service.UserService;
import com.example.library.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public PlainResult<IPage<UserInfoDto>> searchUserInfo(@RequestParam(required = false) Integer pageNum
    ,@RequestParam(required = false) Integer pageSize
    ,@RequestParam(required = false) String name
    ,@RequestParam(required = false) String phone
    ,@RequestParam(required = false) String email){
      IPage<UserInfoDto> ueserInfoPage = userService.searchUser(pageNum,pageSize,name,phone,email);
      return PlainResult.success(ueserInfoPage);
    }

    @PutMapping("/{id}")
    public PlainResult<String> updateUser(@PathVariable Long id, @RequestBody UserInfoDto userInfoDto){
        userService.updateUser(id,userInfoDto);
        return PlainResult.success("seccess");
    }
    @DeleteMapping("/{id}")
    public PlainResult<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return PlainResult.success("seccess");
    }
    @PostMapping("/deleteBatch")
    public PlainResult<String> deleteBatchUser(@RequestParam List<Long> ids){
        userService.deleteBatch(ids);
        return PlainResult.success("seccess");
    }
}
