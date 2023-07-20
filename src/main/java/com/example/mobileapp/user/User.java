package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import com.google.firebase.database.annotations.NotNull;
import jakarta.persistence.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;

    private String fName;
    private String lName;
    @OneToMany
    private List<Listing> listingList;

    private String role;


    public User() {
    }

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
    public User(String username){
        this.username = username;
    }
    public User(Long id, String username, String password, String email, String phoneNumber, String fName, String lName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fName = fName;
        this.lName = lName;
    }

    public User(String username, String password, String email, String phoneNumber, String fName, String lName, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fName = fName;
        this.lName = lName;
        this.role = role;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Listing> getListingList() {
        return listingList;
    }

    public void setListingList(List<Listing> listingList) {
        this.listingList = listingList;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + phoneNumber + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", listingList=" + listingList +
                '}';
    }

}
