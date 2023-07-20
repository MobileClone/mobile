package com.example.mobileapp.user;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
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
    public String registerUserAccount(
            @ModelAttribute("user") User userDto,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        String crypted = userService.cryptInformation(userDto.getPassword());
        userDto.setPassword(crypted);
        userService.addUser(userDto);
        return "redirect:/login";
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
            Errors errors) throws ExecutionException, InterruptedException, NoSuchAlgorithmException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        boolean valid = userService.isValid(userDto.getUsername(), userDto.getPassword());
        if(valid == true){
            return "redirect:/homepageLogged";
        }
        return "redirect:/login";
    }

    @DeleteMapping
    public String deletePatient(@RequestParam Long id){
        return userService.deleteUser(id);
    }

    @PutMapping
    public String updatePatient(@RequestBody Listing listing  ) throws InterruptedException, ExecutionException {
        return userService.updateUser(listing);
    }

}
