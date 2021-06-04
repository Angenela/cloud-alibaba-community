package com.example.userservice.advice;

import com.example.commonapi.constant.UserStatus;
import com.example.userservice.controller.UserController;
import com.example.userservice.exception.UserException;
import com.example.userservice.exception.UserGetException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackageClasses = {UserController.class})
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    @ResponseBody
    public String handler(){
        return UserStatus.USER_REGISTER_FAILURE.toString();
    }

    @ExceptionHandler(value = {UserGetException.class})
    @ResponseBody
    public String handler1(){
        return UserStatus.USER_GET_FAILURE.toString();
    }
}
