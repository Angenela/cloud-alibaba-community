package com.example.commonapi.constant;

public enum UserStatus {
    USER_REGISTER_SUCCESS(1),
    USER_REGISTER_FAILURE(2),
    USER_GET_FAILURE(3);

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    UserStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(this.status);
    }
}
