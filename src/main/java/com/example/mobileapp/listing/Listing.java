package com.example.mobileapp.listing;

import com.google.firebase.database.annotations.NotNull;
import jakarta.persistence.*;

@Entity
@Table
public class Listing {

    @Id
    private Long id;
   private String brand;
   private String model;
   private String description;
   private String imageUrl;
   private double price;
   private int kilometers;
   private String yearOfMaking;
   private int userId;

   public Listing(){}

    public Listing(Long id,String brand, String model, String description, String imageUrl, double price, int kilometers, String yearOfMaking, int userId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.kilometers = kilometers;
        this.yearOfMaking = yearOfMaking;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getYearOfMaking() {
        return yearOfMaking;
    }

    public void setYearOfMaking(String yearOfMaking) {
        this.yearOfMaking = yearOfMaking;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
