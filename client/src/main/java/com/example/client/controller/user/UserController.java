package com.example.client.controller.user;

import com.example.commonapi.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        logger.info("\nid为" + user.getId() + "的用户退出了登录");
        return "forward:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/user")
    public String userHome() {
        return "user";
    }

    @RequestMapping("/user/info")
    public String userInfo() {
        return "page/userInfo";
    }

    @RequestMapping("/user/update")
    public String userUpdate() {
        return "page/update";
    }
}
