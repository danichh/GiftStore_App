// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.Models;

public class Flower extends Product{

    /**
     * Product class constructor
     *
     * @param id       the id of the product
     * @param image    the image of the product
     * @param title    the name of the product
     * @param price    the price of the product
     * @param favorite whether it has been added to favorite or not
     */
    public Flower(int id, int image, String title, double price, int favorite) {
        super(id, image, title, price, favorite);
    }
}
