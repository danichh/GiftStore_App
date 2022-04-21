package com.example.assignment02.Util;

import android.util.Log;

import com.example.assignment02.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a singleton class that keeps cart products
 */
public class ShoppingCart {
    private static  ShoppingCart instance;
    private final List<Product> products;

    public static ShoppingCart getInstance() {
        if(instance == null){
            instance = new ShoppingCart();
        }
        return instance;
    }

    private ShoppingCart() {
        products = new ArrayList<>();
    }

    /**
     * Adds item to cart
     * @param product the product to add
     */
    public void addItemToCart(Product product) {
        products.add(product);
        Log.d("ShoppingCart", products.size() + " ");
    }

    /**
     * Removes product passed from cart
     * @param product the product to remove
     */
    public void removeFromCart(Product product) {
        products.remove(product);
    }

    public List<Product> getproducts() {
        return products;
    }

    /**
     * Clear products in the cart
     */
    public void clearproducts() {
        products.clear();
    }
}