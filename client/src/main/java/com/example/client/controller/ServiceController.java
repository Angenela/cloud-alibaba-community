package com.example.client.controller;

import com.example.commonapi.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ServiceController {
    private static final Log logger = LogFactory.getLog(ServiceController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/register")
    public String register(
            @RequestBody User user,
            HttpServletRequest request
    ) throws JsonProcessingException {

        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        String path = String.format(
                "http://%s:%s/%s",
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                "user"
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                objectMapper.writeValueAsString(user),
                headers
        );
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(path, requestEntity, String.class);

        logger.info("\t/register" + "\t注册用户消息：" + user.toString() + "\tstatus：" + responseEntity.getBody());

        //  将用户消息保存到 Session
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());

        return responseEntity.getBody();
    }
}
