package com.example.userservice.service;


import com.example.commonapi.pojo.User;

public interface UserService {
    //    添加一个新用户
    int addUser(User user);
    //    根据Id获取一个用户
    User getUser(int id);

    void updateUsername(int id, String username);
}


