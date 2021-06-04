package com.example.userservice.controller;

import com.example.commonapi.constant.UserStatus;
import com.example.commonapi.pojo.User;
import com.example.userservice.exception.UserException;
import com.example.userservice.exception.UserGetException;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper jsonService;

    private static Log logger = LogFactory.getLog(UserController.class);
    
    @PostMapping("/user")
    public String addUser(User user) throws Exception {
        try {
            userService.addUser(user);
        } catch (Exception exception) {
            logger.error("用户注册失败", exception);
            throw new UserException("用户注册失败");
        }
        return jsonService.writeValueAsString(UserStatus.USER_REGISTER_SUCCESS);
    }

    @GetMapping("/user")
    public String getUser(int id) throws Exception {
        User user ;
        try {
            user= userService.getUser(id);
        } catch (Exception exception) {
            logger.error("获取用户失败", exception);
            throw new UserGetException("获取用户失败");
        }

        return jsonService.writeValueAsString(user);
    }

    @PutMapping("/user")
    public String updateUsername(int id,String username) throws JsonProcessingException {
        userService.updateUsername(id,username);
        return jsonService.writeValueAsString(2);
    }

}
