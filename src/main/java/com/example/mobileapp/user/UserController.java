package com.example.mobileapp.user;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {this.userService = userService;}
    @GetMapping(value = "/useradd")
    public void addUser() throws ExecutionException, InterruptedException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        userService.addUser();
    }
}
