package com.example.mobileapp.listing;

import com.example.mobileapp.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ListingController {

    private final ListingService listingService;

   @Autowired
    public ListingController(ListingService listingService) {
       this.listingService = listingService;
    }
//
//    @GetMapping
//    public List<Listing> getListings(){
//
//        return listingService.getListings();
//    }

    @GetMapping(value = "/list")
    public void addListing() throws ExecutionException, InterruptedException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        listingService.addListing();
    }
}
