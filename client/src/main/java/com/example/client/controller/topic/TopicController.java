package com.example.client.controller.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopicController {
    @RequestMapping("/test")
    public String testMD() {
        return "testMD";
    }

    @RequestMapping("/user/topic")
    public String userTopic() {
        return "page/userTopic";
    }

    @RequestMapping("/user/create")
    public String userCreateTopic() {
        return "page/userCreateTopic";
    }
}
