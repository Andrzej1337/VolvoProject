package org.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainService {

    public static void main(String[] args) {
        SpringApplication.run(MainService.class);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
