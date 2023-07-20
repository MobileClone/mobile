package com.example.mobileapp.listing;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.user.User;
import com.google.firebase.database.DataSnapshot;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
       this.listingService = listingService;
    }

    @GetMapping("/getListingDetails")
    public Listing getListing(@RequestParam Long id ) throws InterruptedException, ExecutionException{
        return listingService.getListing(id);
    }

    @PutMapping("/updateListing")
    public String updatePatient(@RequestBody Listing listing  ) throws InterruptedException, ExecutionException {
        return listingService.updateListing(listing);
    }

    @DeleteMapping("/deleteListing")
    public String deletePatient(@RequestParam Long id){
        return listingService.deleteListing(id);
    }

    @GetMapping("/createListing")
    public String createListing(Model model){
        Listing listing = new Listing();
        model.addAttribute("listing",listing);
        return "createListing";
    }

    @PostMapping("/createListing")
    public String createListing(
            @ModelAttribute("listing") Listing listing,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        listingService.createListing(listing);
        return "redirect:/homepageLogged";
    }

    @GetMapping (value = "/")
    public String getAllListings(Model model) throws ExecutionException, InterruptedException {
        List<Listing> listingList = listingService.getAllListings();
        model.addAttribute("listings",listingList);
        return "homepage";
    }

    @GetMapping (value = "/homepageLogged")
    public String getAllListingsLogged(Model model) throws ExecutionException, InterruptedException {
        List<Listing> listingList = listingService.getAllListings();
        model.addAttribute("listings",listingList);
        return "homepageLogged";
    }

    @GetMapping (value = "/listings")
    public String getAllUsers(Model model) throws ExecutionException, InterruptedException {
        List<Listing> listingList = listingService.getAllListings();
        model.addAttribute("listings",listingList);
        return "listings";
    }
}
