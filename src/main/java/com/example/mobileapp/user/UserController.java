package com.example.mobileapp.user;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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

    @GetMapping (value = "/users")
    public String getAllUsers(Model model) throws ExecutionException, InterruptedException {
       List<User> userList = userService.getAll();
        model.addAttribute("users",userList);
        return "user";
    }

    @GetMapping("/register")
    public String register(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public void registerUserAccount(
            @ModelAttribute("user") User userDto,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        userService.addUser(userDto);
        System.out.println(userDto);
    }

    @GetMapping("/login")
    public String login(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String loginUserAccount(
            @ModelAttribute("user") User userDto,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        boolean valid = userService.isValid(userDto.getUsername(), userDto.getPassword());
        if(valid == true){
            return "redirect:/homepageLogged";
        }
        return "redirect:/";
    }

}
