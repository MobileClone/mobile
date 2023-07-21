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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(value = "/profilepage")
    public String getUserProfile(Model model) throws ExecutionException, InterruptedException{
        List<User> userList = userService.getAll();
        User user = new User();
        String username = userService.getUsername("$2a$10$8SyHRPMU/NRL/p9qEcPmJeUJDgdwzQACiovsnnPv.2i7X87YQ8Ksq");
        for (User user1 : userList){
            if(username.equals(user1.getUsername())){
                user.setId(user1.getId());
                user.setUsername(username);
                user.setEmail(user1.getEmail());
                user.setfName(user1.getfName());
                user.setlName(user1.getlName());
                user.setPhoneNumber(user1.getPhoneNumber());
            }
        }
        ListingService listingService = new ListingService();
        List<Listing> listingList = listingService.getAllListings();
        List<Listing> listingList1 = new ArrayList<>();
        for(Listing listing : listingList){
            if(user.getId() == listing.getUserId()){
                listingList1.add(listing);
            }
        }
        model.addAttribute("listings",listingList1);
        model.addAttribute("user",user);
        return "profilepage";
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
        userDto.setToken(userService.cryptInformation(userDto.getUsername()));
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
    public ModelAndView loginUserAccount(
            @ModelAttribute("user") User userDto,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException, NoSuchAlgorithmException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        String token = userService.getUserToken(userDto.getUsername());
        boolean valid = userService.isValid(userDto.getUsername(), userDto.getPassword());
        if(valid == true){
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/homepageLogged?token=" + token);
            return new ModelAndView(redirectView);
        }
        return new ModelAndView();
    }

    @GetMapping("/home")
    public ModelAndView test(@RequestParam(name = "token", required = false) String customToken) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = new ModelAndView("/homepageLogged");
        if(customToken != null){
            boolean check = userService.isValidToken(customToken);
            if(check){
                Long id = userService.getUserId(customToken);
                System.out.println(id);
            } else{
                System.out.println("nice try");
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/login");
                return new ModelAndView(redirectView);
            }
        }else{
            System.out.println("nice try");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/login");
            return new ModelAndView(redirectView);
        }
        return modelAndView;
    }
    @DeleteMapping
    public String deletePatient(@RequestParam Long id){
        return userService.deleteUser(id);
    }

}
