package com.example.mobileapp;

import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import com.example.mobileapp.user.User;
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

import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "homepage";
    }

    @GetMapping("/createListing")
    public String createListing(Model model){
        Listing listing = new Listing();
        model.addAttribute("listing",listing);
        return "createListing";
    }

    @PostMapping("/createListing")
    public void createListing(
            @ModelAttribute("listing") Listing listing,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        ListingService listingService = new ListingService();
        listingService.createListing(listing);
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
        UserService userService = new UserService();
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
        UserService userService = new UserService();
        boolean valid = userService.isValid(userDto.getUsername(), userDto.getPassword());
        if(valid == true){
            return "homepage";
        }
        return "home";
    }

}
