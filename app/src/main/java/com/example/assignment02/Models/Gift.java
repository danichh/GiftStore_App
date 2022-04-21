package com.example.assignment02.Models;

public class Gift extends Product{

    /**
     * Product class constructor
     *
     * @param id       the id of the product
     * @param image    the image of the product
     * @param title    the name of the product
     * @param price    the price of the product
     * @param favorite whether it has been added to favorite or not
     */
    public Gift(int id, int image, String title, double price, int favorite) {
        super(id, image, title, price, favorite);
    }
}
