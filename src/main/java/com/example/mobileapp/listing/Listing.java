package com.example.mobileapp.listing;

public class Listing {

    private Long id;
    private String title;
    private double price;
    private String characteristics;
    private String comment;

    public Listing() {
    }

    public Listing(Long id, String title, double price, String characteristics, String comment) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.characteristics = characteristics;
        this.comment = comment;
    }

    public Listing(String title, double price, String characteristics, String comment) {
        this.title = title;
        this.price = price;
        this.characteristics = characteristics;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", characteristics='" + characteristics + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }


}
