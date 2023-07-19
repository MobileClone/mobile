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

}
