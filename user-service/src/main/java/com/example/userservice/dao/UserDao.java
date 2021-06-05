package com.example.userservice.dao;

import com.example.commonapi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
//    添加一个新用户
    int addUser(User user);

//    根据Id获取一个用户
    User getUserByUsername(String username);

//    修改username
    void updateUsername(@Param(value = "id") int id, @Param(value = "username") String username);

//    删除user
    void deleteUser(int id);

//    修改信息
    void updateInfo(User user);

//    查重
    String existUsername(@Param(value = "username") String username);


//    修改密码
    void updateUserPassword(@Param(value = "username") String username,@Param(value = "password") String password);
}
