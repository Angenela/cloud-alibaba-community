package com.example.commentservice.controller;


import com.example.commentservice.service.CommentService;
import com.example.commonapi.pojo.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ObjectMapper jsonService;

    @PostMapping("/comment")
    public String addComment(Comment comment) throws JsonProcessingException {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=new Date();

        comment.setTime(formatter.format(time));

        commentService.addComment(comment);

        return jsonService.writeValueAsString("");
    }





}
