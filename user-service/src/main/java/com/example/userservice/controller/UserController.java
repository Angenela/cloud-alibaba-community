package com.example.userservice.controller;

import com.example.commonapi.constant.SystemStatus;
import com.example.commonapi.constant.UserStatus;
import com.example.commonapi.dto.UserStatusDTO;
import com.example.commonapi.pojo.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private static Log logger = LogFactory.getLog(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper jsonService;

    @PostMapping("/user")
    public String addUser(@RequestBody User user) throws Exception {
        logger.info("POST /user\t" + "user: " + user.toString());

        UserStatusDTO userStatus = new UserStatusDTO();

        try {
            if (userService.existUsername(user.getUsername())) {
                userStatus.setStatus(UserStatus.USER_EXIST.getStatus());
                logger.warn("POST /user username: " + user.getUsername() + " 已存在");
                return jsonService.writeValueAsString(userStatus);
            }
            userService.addUser(user);
            userStatus.setUser(user);
            userStatus.setStatus(UserStatus.USER_REGISTER_SUCCESS.getStatus());
        } catch (Exception exception) {
            userStatus.setStatus(SystemStatus.SYSTEM_ERROR.getStatus());
            logger.error("用户注册失败，系统出现错误 返回值为：" + userStatus, exception);
        }

        return jsonService.writeValueAsString(userStatus);
    }

    @GetMapping("/user")
    public String getUserByUsername(String username) throws JsonProcessingException {
        logger.info("GET /user\t" + "username: " + username);
        UserStatusDTO userStatus = new UserStatusDTO();


        try {
            User user = userService.getUserByUsername(username);
            if (user == null) {
                userStatus.setStatus(UserStatus.USER_NOT_EXIST.getStatus());
                return jsonService.writeValueAsString(userStatus);
            }
            userStatus.setStatus(UserStatus.USER_EXIST.getStatus());
            userStatus.setUser(user);
        } catch (Exception e) {
            logger.error("用户获取失败，系统出现错误", e);
            userStatus.setStatus(SystemStatus.SYSTEM_ERROR.getStatus());
        }

        return jsonService.writeValueAsString(userStatus);
    }

    @PostMapping("/user/password")
    public String updateUserPassword(String username, String password) throws JsonProcessingException {
        logger.info("Post /user\t username:" + username + "\tpassword:" + password);

        UserStatusDTO userStatus = new UserStatusDTO();
        try {
            userService.updateUserPassword(username, password);
            User user = userService.getUserByUsername(username);
            userStatus.setStatus(UserStatus.USER_UPDATE_PASSWORD_SUCCESS.getStatus());
            userStatus.setUser(user);
        } catch (Exception e) {
            logger.error("用户获取失败，系统出现错误", e);
            userStatus.setStatus(SystemStatus.SYSTEM_ERROR.getStatus());
        }

        return jsonService.writeValueAsString(userStatus);
    }
}
