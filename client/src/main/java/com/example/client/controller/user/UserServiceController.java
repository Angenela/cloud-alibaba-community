package com.example.client.controller.user;

import com.example.commonapi.constant.UserStatus;
import com.example.commonapi.dto.Status;
import com.example.commonapi.dto.UpdatePasswordDTO;
import com.example.commonapi.dto.UserStatusDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserServiceController {
    private static final Log logger = LogFactory.getLog(UserServiceController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletRequest request) throws JsonProcessingException {
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

        logger.info("\nPOST /register" + "\n?????????????????????" + user.toString() + "\n???????????????" + responseEntity.getBody());

        //  ???????????????????????? Session
        UserStatusDTO userStatus = objectMapper.readValue(responseEntity.getBody(), UserStatusDTO.class);
        HttpSession session = request.getSession();
        session.setAttribute("user", userStatus.getUser());

        Status status = new Status();
        status.setStatus(userStatus.getStatus());
        return objectMapper.writeValueAsString(status);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletRequest request) throws JsonProcessingException {

        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        String path = String.format(
                "http://%s:%s/%s?username={username}",
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                "user"
        );
        String response = restTemplate.getForObject(path, String.class, user.getUsername());

        // ???????????????????????? UserStatus
        UserStatusDTO userStatus = objectMapper.readValue(response, UserStatusDTO.class);

        // ???????????????????????? Status
        Status status = new Status();
        status.setStatus(userStatus.getStatus());

        // ???????????????????????????????????????????????????
        if (userStatus.getUser() != null) {
            if (userStatus.getUser().getPassword().equals(user.getPassword())) {
                status.setStatus(UserStatus.USER_LOGIN_SUCCESS.getStatus());
                //  ???????????????????????? Session
                HttpSession session = request.getSession();
                session.setAttribute("user", userStatus.getUser());
            } else
                status.setStatus(UserStatus.USER_LOGIN_FAILURE.getStatus());
        }

        logger.info(
                "\nPOST /login" +
                        "\n?????????????????????" + user.toString() +
                        "\n?????????????????????" + response +
                        "\n????????????????????????????????????" + status.getStatus()
        );

        return objectMapper.writeValueAsString(status);
    }

    @PutMapping("`/user/password")
    public String updatePassword(
            @RequestBody UpdatePasswordDTO passwordDTO,
            HttpServletRequest request
    ) throws JsonProcessingException {
        logger.info(
                "\n PUT /user/password" +
                        "\n passwordDTO: " + passwordDTO.toString()
        );

        // ??????????????????
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        String path = String.format(
                "http://%s:%s/%s",
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                "user/password"
        );

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ??????????????????
        Status status = new Status();

        // ???????????????????????????
        if (user.getPassword().equals(passwordDTO.getOldPassword())) {
            // ????????????????????????
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(
                    objectMapper.writeValueAsString(user),
                    headers
            );
            restTemplate.postForEntity(path, requestEntity, String.class);

            // ??????session??????
            session.removeAttribute("user");

            status.setStatus(UserStatus.USER_UPDATE_PASSWORD_SUCCESS.getStatus());
        } else {
            // ??????????????????
            status.setStatus(UserStatus.USER_OLD_PASSWORD_FAILURE.getStatus());
            return objectMapper.writeValueAsString(status);
        }

        logger.info(
                "\n PUT /user/password" +
                        "\n status: " + status
        );

        return objectMapper.writeValueAsString(status);
    }
}
