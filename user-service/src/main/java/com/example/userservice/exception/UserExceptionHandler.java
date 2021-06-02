package com.example.userservice.exception;

import com.example.userservice.constant.Status;
import com.example.userservice.controller.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {UserController.class})
public class UserExceptionHandler {
    @Autowired
    private ObjectMapper jsonService;

    @ExceptionHandler
    public String handler(Exception exception) throws JsonProcessingException {
        return jsonService.writeValueAsString(Status.USER_REGISTER_FAILURE);
    }
}
