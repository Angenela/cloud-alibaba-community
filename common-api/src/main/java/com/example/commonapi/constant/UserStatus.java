package com.example.commonapi.constant;

import java.io.Serializable;

public enum UserStatus implements Serializable {
    USER_REGISTER_SUCCESS(1),
    USER_REGISTER_FAILURE(2),
    USER_GET_FAILURE(3),
    USER_UPDATE_FAILURE(4),
    USER_UPDATE_SUCCESS(5),
    USER_EXIST(6),
    USER_NOT_EXIST(7),
    USER_PASSWORD_EMPTY(8),
    USER_LOGIN_SUCCESS(9),
    USER_LOGIN_FAILURE(10),
    USER_UPDATE_PASSWORD_SUCCESS(11),
    USER_OLD_PASSWORD_FAILURE(12);

    private int status;

    UserStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
