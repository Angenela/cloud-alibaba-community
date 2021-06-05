package com.example.commonapi.dto;

import com.example.commonapi.pojo.User;

/**
 * 在注册成功后返回，插入的 User 和 status
 */
public class UserStatusDTO {
    private User user;
    private int status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
