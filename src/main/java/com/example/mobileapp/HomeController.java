package com.example.mobileapp;

import com.example.mobileapp.user.User;
import com.example.mobileapp.user.UserDto;
import com.example.mobileapp.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutionException;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "homepage";
    }

    @GetMapping("/createListing")
    public String createListing(){
        return "createListing";
    }

    @GetMapping("/login")
    public String login(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/login")
    public void registerUserAccount(
            @ModelAttribute("user") User userDto,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        UserService userService = new UserService();
        userService.addUser(userDto);
        System.out.println(userDto);
    }
}
