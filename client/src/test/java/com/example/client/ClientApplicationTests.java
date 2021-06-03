package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest
class ClientApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Test
	void contextLoads() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
		String path = String.format("http://%s:%s/%s", serviceInstance.getHost(), serviceInstance.getPort(), "user");
		System.out.println(restTemplate.postForObject(path, "", String.class, new HashMap<String, String>()));
	}

}
