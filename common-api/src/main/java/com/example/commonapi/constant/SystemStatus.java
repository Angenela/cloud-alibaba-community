package com.example.commonapi.constant;

public enum SystemStatus {
    SYSTEM_ERROR(500);

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    SystemStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(this.status);
    }
}
