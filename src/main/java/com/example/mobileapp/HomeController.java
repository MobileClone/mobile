package com.example.mobileapp;

import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import com.example.mobileapp.user.User;
import com.example.mobileapp.user.UserService;
import com.google.protobuf.Value;
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

    public String home(){
        return "homepage";
    }


@GetMapping(value = "/profilepage")
    public String userpage(){
        return "profilepage";
    }
}
