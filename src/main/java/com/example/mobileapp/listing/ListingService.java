package com.example.mobileapp.listing;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    public List<Listing> getListings(){

        return List.of(new Listing(1L, "Audi A8", 8000, "3L engine", "Good condition"));
    }

    public void addNewListing(Listing listing){

    }
}
