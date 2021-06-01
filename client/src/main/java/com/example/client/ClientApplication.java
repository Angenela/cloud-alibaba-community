package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@PropertySource(value = {"classpath:kafka.properties"})
public class ClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
//        System.err.println(environment.getProperty("spring.kafka.bootstrap-servers"));
    }
}
