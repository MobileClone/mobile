package com.example.mobileapp.user;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {this.userService = userService;}
    @GetMapping(value = "/useradd")
    public void addUser() throws ExecutionException, InterruptedException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        User user = new User();
        userService.addUser(user);
    }

    @GetMapping (value = "/users")
    public String getAllUsers(Model model) throws ExecutionException, InterruptedException {
       List<User> userList = userService.getAll();
        model.addAttribute("users",userList);
        return "user";
    }





}
