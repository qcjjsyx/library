package com.example.library.security;


import com.example.library.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.library.domain.User user = userMapper.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: "+username);
        }
        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

}
