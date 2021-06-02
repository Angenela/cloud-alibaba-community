package com.example.userservice.service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //调用dao层的操作，设置一个set接口，方便Spring管理
    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.addUser(user);

    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }


}