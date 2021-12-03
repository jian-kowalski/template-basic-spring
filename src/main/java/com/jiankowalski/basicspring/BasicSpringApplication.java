package com.jiankowalski.basicspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jiankowalski")
public class BasicSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringApplication.class, args);
    }

}
