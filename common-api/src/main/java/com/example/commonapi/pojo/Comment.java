package com.example.commonapi.pojo;

public class Comment {
    private int id;
    private int topicId;
    private int userId;
    private String content;
    private String time;


    public Comment(int topicId, int userId, String content) {
        this.topicId = topicId;
        this.userId = userId;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
