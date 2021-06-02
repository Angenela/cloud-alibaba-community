package com.example.userservice.dao;

import com.example.userservice.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
//    添加一个新用户
    int addUser(User user);

//    根据Id获取一个用户
    User getUser(int id);
}
