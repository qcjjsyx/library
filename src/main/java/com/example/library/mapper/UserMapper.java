package com.example.library.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.library.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-09-11 16:17:59
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User findOneByUsername(@Param("username") String username);
}




