package com.example.userservice;


import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.updateUsername(9, "lmz");
    }

}
