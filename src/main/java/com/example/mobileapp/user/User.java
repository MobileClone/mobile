package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import java.util.List;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String telephoneNumber;
    private List<Listing> listingList;

    public User() {
    }

    public User(Long id, String username, String password, String email, String telephoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public User(String username, String password, String email, String telephoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Listing> getListingList() {
        return listingList;
    }

    public void setListingList(List<Listing> listingList) {
        this.listingList = listingList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", listingList=" + listingList +
                '}';
    }
}
