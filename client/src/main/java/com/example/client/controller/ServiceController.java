package com.example.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ServiceController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public String register(HttpServletRequest request, User user) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");

        String path = String.format("http://%s:%s/%s", serviceInstance.getHost(), serviceInstance.getPort(), "user");

        String status = restTemplate.postForObject(path, user, String.class);
//        objectMapper.readValue(status, )
        request.setAttribute("status", status);

        return "redirect:/";
    }
}
