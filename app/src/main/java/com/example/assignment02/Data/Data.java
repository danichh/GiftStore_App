// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.Data;

import com.example.assignment02.Models.Flower;
import com.example.assignment02.Models.Gift;
import com.example.assignment02.Models.Product;
import com.example.assignment02.R;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        // add all the flower
        products.add(new Flower(1, R.drawable.roseflower, "Rose Flower", 9.99f, 0 ));
        products.add(new Flower(2, R.drawable.amaryllis, "Amaryllis Flower", 22.99f, 0 ));
        products.add(new Flower(3, R.drawable.angleliquetulip, "Angelique Tulip Flower", 15.99f, 0 ));
        products.add(new Flower(4, R.drawable.plumeria, "Plumeria Flower", 9.99f, 0 ));
        products.add(new Flower(5, R.drawable.bouquetofflower, "Amaryllis Flower", 29.99f, 0 ));

        // add all the gift
        products.add(new Gift(6, R.drawable.watch, "Analog Watch", 88.99f, 0));
        products.add(new Gift(7, R.drawable.friendshipbracelet, "FriendShip Bracelet", 9.99f, 0));
        products.add(new Gift(8, R.drawable.giftbox, "Gift Box", 49.99f, 0));
        products.add(new Gift(9, R.drawable.necklace, "Necklace", 19.99f, 0));
        products.add(new Gift(10, R.drawable.perfume, "Perfume", 69.99f, 0));

        return products;
    };
}
