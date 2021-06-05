package com.example.userservice.service;


import com.example.commonapi.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    //    添加一个新用户
    int addUser(User user);
    //    根据Id获取一个用户
    User getUserByUsername(String username);

    void updateUsername(int id, String username);

    void deleteUser(int id);

    boolean existUsername( String username);

    void updateUserPassword(String username,String password);
}


