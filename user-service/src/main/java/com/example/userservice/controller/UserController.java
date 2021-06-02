package com.example.userservice.controller;

import com.example.userservice.constant.Status;
import com.example.userservice.pojo.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper jsonService;
    
    @PostMapping("/user")
    public String addUserLogin(User user) throws JsonProcessingException {
        userService.addUser(user);
        return jsonService.writeValueAsString(1);
    }

    @GetMapping("/user")
    public String getUser(int id) throws JsonProcessingException {
        User user = userService.getUser(id);
        return jsonService.writeValueAsString(user);
    }



}
