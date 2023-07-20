package com.example.mobileapp;

import com.example.mobileapp.user.UserController;
import com.example.mobileapp.user.UserService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@SpringBootApplication
public class MobileAppApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        UserService us = new UserService();
        us.createSHAHash("frendo");
        String crypted = us.cryptInformation("frendo");
        us.checkCryptedInformation(crypted,"frendo");

        SpringApplication.run(MobileAppApplication.class, args);
    }

}
