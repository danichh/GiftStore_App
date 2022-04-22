// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.Models;

/**
 * This class models any product to be sold
 */
public abstract class Product {
    private int id;
    private int image;
    private String title;
    private double price;
    private int favorite;

    public Product() {
    }

    /**
     * Product class constructor
     * @param id the id of the product
     * @param image the image of the product
     * @param title the name of the product
     * @param price the price of the product
     * @param favorite whether it has been added to favorite or not
     */
    public Product(int id, int image, String title, double price, int favorite) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
        this.favorite = favorite;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
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
    public void setPrice(float price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFavorite() {
        return favorite;
    }
    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}

