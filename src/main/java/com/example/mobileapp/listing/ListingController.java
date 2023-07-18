package com.example.mobileapp.listing;

import com.example.mobileapp.DbConnection;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
       this.listingService = listingService;
    }

    @GetMapping(value = "/list")
    public void addListing() throws ExecutionException, InterruptedException {
        DbConnection db = new DbConnection();
        db.dbConnection();
        listingService.addListing();
    }

    @GetMapping("/getPatientDetails")
    public Listing getListing(@RequestParam Long id ) throws InterruptedException, ExecutionException{
        return listingService.getListing(id);
    }

    @PostMapping("/createPatient")
    public String createListing(@RequestBody Listing listing ) throws InterruptedException, ExecutionException {
        return listingService.saveListing(listing);
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Listing listing  ) throws InterruptedException, ExecutionException {
        return listingService.updateListing(listing);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam Long id){
        return listingService.deleteListing(id);
    }

}
