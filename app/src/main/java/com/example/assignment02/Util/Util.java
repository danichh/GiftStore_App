// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.Util;

import com.example.assignment02.Models.Flower;
import com.example.assignment02.Models.Gift;

/**
 * List of String that on use often
 */
public class Util {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Store";


    public static final String _ID = "id";
    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_IMAGE = "image";
    public static final String COLUMN_NAME_PRICE = "price";
    public static final String COLUMN_NAME_FAVORITE = "favorite";
    public static final String COLUMN_NAME_TYPE = "type";

    public static final String FLOWER_CLASS = Flower.class.getName();
    public static final String GIFT_CLASS = Gift.class.getName();
}
