package com.example.mobileapp;

import com.example.mobileapp.listing.Listing;
import com.example.mobileapp.listing.ListingService;
import com.example.mobileapp.user.User;
import com.example.mobileapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class AdminController {

    private final ListingService listingService;
    private final UserService userService;

    public AdminController(ListingService listingService, UserService userService) {
        this.listingService = listingService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) throws ExecutionException, InterruptedException {
        List<Listing> listings = listingService.getAllListings();
        List<User> users = userService.getAll();
        model.addAttribute("listings", listings);
        model.addAttribute("users", users);

        return "admin";
    }


    @PostMapping("/editListing")
    public String editListing(@RequestParam Long listingId) {
        return "redirect:/admin";
    }

    @PostMapping("/deleteListing")
    public String deleteListing(@RequestParam Long listingId) {
        String deleteMessage = listingService.deleteListing(listingId);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUserListing")
    public String deleteSomething(Model model) throws ExecutionException, InterruptedException {
        return "redirect:/profilepage";
    }

    @PostMapping("/deleteUserListing")
    public String deleteUserListing(@RequestParam Long listingId) {
        String deleteMessage = listingService.deleteListing(listingId);
        return "redirect:/profilepage";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam Long userId) {
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        String deleteUser = userService.deleteUser(userId);
        return "redirect:/admin";
    }
}
