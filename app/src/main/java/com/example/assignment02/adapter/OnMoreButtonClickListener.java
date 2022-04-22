// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.adapter;

import android.view.MenuItem;
import android.view.View;

import com.example.assignment02.Models.Product;

public interface OnMoreButtonClickListener {
    void onItemClick(View view, Product obj, MenuItem item);
}
