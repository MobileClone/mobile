package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUser(){

        return List.of(new User(1L, "ivan21", "883391", "ivan21@abv.bg", "0889213347"));
    }

    public void addNewUser(User user){

    }
}
