// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.Util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.assignment02.Data.Data;
import com.example.assignment02.Models.Flower;
import com.example.assignment02.Models.Gift;
import com.example.assignment02.Models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the Product Table
         String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Util.PRODUCT_TABLE_NAME + " (" +
                        Util._ID + " INTEGER PRIMARY KEY," +
                        Util.COLUMN_NAME_TITLE + " TEXT," +
                        Util.COLUMN_NAME_TYPE + " TEXT," +
                        Util.COLUMN_NAME_PRICE + " REAL," +
                        Util.COLUMN_NAME_FAVORITE + " INTEGER DEFAULT 0," +
                        Util.COLUMN_NAME_IMAGE + " INTEGER)";
        db.execSQL(SQL_CREATE_ENTRIES);

        // Insert all the product inside of the table
        List<Product> products = Data.getAllProducts();
        for (Product product : products){
            db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (%s, \"%s\", %d, %f, \"%s\", %d)",
                    Util.PRODUCT_TABLE_NAME,Util._ID, Util.COLUMN_NAME_TITLE, Util.COLUMN_NAME_IMAGE, Util.COLUMN_NAME_PRICE, Util.COLUMN_NAME_TYPE, Util.COLUMN_NAME_FAVORITE,
                    product.getId(),product.getTitle(), product.getImage(), product.getPrice(), product.getClass().getName(), product.getFavorite()));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.PRODUCT_TABLE_NAME);
        onCreate(db);
    }
    //=====================================CRUD========================================//

    /**
     *  get the list of Product base on it type
     * @param type is the given type
     * @return arrayList of the given type products
     */
    public List<Product> getProductsByType(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> products = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM PRODUCTS WHERE %s = \"%s\"", Util.COLUMN_NAME_TYPE, type);
        Cursor cursor = db.rawQuery(SQL_SELECT, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String theType = cursor.getString(2);
                double price = cursor.getDouble(3);
                int favorite = cursor.getInt(4);
                int image = cursor.getInt(5);

                if(theType.equalsIgnoreCase(Util.FLOWER_CLASS) ){
                    products.add(new Flower(id,image,title,price,favorite));
                }else{
                    products.add(new Gift(id,image,title,price,favorite));
                }
            }while (cursor.moveToNext());
        }

        Log.d("database", products.size() + "");
        return products;

    }

    /**
     * get All the favorite products
     * @return arraylist of the favorite products
     */
    public List<Product> getFavorite(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> products = new ArrayList<>();
        String SQL_SELECT = String.format("SELECT * FROM PRODUCTS WHERE %s = 1", Util.COLUMN_NAME_FAVORITE);
        Cursor cursor = db.rawQuery(SQL_SELECT, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String theType = cursor.getString(2);
                double price = cursor.getDouble(3);
                int favorite = cursor.getInt(4);
                int image = cursor.getInt(5);

                if(theType.equalsIgnoreCase(Util.FLOWER_CLASS) ){
                    products.add(new Flower(id,image,title,price,favorite));
                }else{
                    products.add(new Gift(id,image,title,price,favorite));
                }
            }while (cursor.moveToNext());
        }

        Log.d("database", products.size() + "");
        return products;
    }

    /**
     * Update the favorite columne
     * @param product_id is the id of the needed update product
     * @param fav is the value 1= fav 0= no fav
     * @return
     */
    public int updateFavorite(int product_id, int fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.COLUMN_NAME_FAVORITE, fav);

        return db.update(Util.PRODUCT_TABLE_NAME, values, Util._ID + "=?", new String[]{String.valueOf(product_id)});
    }
}
