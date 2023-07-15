package com.example.mobileapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MobileAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileAppApplication.class, args);
    }

}
