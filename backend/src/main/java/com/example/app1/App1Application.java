package com.example.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.model")
@EnableJpaRepositories(basePackages = "com.repository")
public class App1Application {
    public static void main(String[] args) {
        SpringApplication.run(App1Application.class, args);
    }
}
