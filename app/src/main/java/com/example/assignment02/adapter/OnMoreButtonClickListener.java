package com.example.assignment02.adapter;

import android.view.MenuItem;
import android.view.View;

import com.example.assignment02.Models.Product;

public interface OnMoreButtonClickListener {
    void onItemClick(View view, Product obj, MenuItem item);
}
