package com.example.userservice.service;

import com.example.commonapi.pojo.User;
import com.example.userservice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


    public void updateUsername(int id, String username) {
        userDao.updateUsername(id, username);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public boolean existUsername(String username) {
        return userDao.existUsername(username) != null ? true :false;
    }

    public void updateUserPassword(String username, String password) {
        userDao.updateUserPassword(username,password);

    }

}
