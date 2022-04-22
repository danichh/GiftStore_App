// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment02.Models.Flower;
import com.example.assignment02.Models.Gift;
import com.example.assignment02.Models.Product;
import com.example.assignment02.Util.DatabaseHandler;
import com.example.assignment02.Util.Util;
import com.example.assignment02.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {

    private String type;
    private DatabaseHandler db;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ProductFragment() {
    }

    public static ProductFragment newInstance(String type) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            type = getArguments().getString("type");
        }
        db = new DatabaseHandler(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ProductFragment","Type: " + type);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<Product> products =getProducts(type);

        ProductAdapter productAdapter = new ProductAdapter(getActivity().getApplicationContext().getTheme(), getActivity(), products);
        recyclerView.setAdapter(productAdapter);

        // when the share is click, send to the Action_send intent
        productAdapter.setOnMoreButtonClickListener((viewClicked, obj, item)->{
            if(item.getItemId() == R.id.share_btn){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Product details\nName: " + obj.getTitle() + "\nPrice: $" + obj.getPrice());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        /**
         * when swipe to refresh
         * reload the products
         */
        swipeRefreshLayout.setOnRefreshListener(() -> {
            List<Product> items = getProducts(type);
            productAdapter.setItems(items);
            swipeRefreshLayout.setRefreshing(false);
        });


    }

    /**
     * Get the product from the database base on the type
     * @param type is the given type(gift, flower, favorite)
     * @return return all the product of the given type
     */
    private List<Product> getProducts(String type){
        List<Product> products = new ArrayList<>();
        
        if(type.equalsIgnoreCase(Util.FLOWER_CLASS)){
            products = db.getProductsByType(Util.FLOWER_CLASS);
            Log.d("ProductFragment", "Flower "+ products.size() + "");
        }else if (type.equalsIgnoreCase("favorites")){
            products = db.getFavorite();
            Log.d("ProductFragment", "Favorite "+ products.size() + "");
        } else if (type.equalsIgnoreCase(Util.GIFT_CLASS)){
            products = db.getProductsByType(Util.GIFT_CLASS);
            Log.d("ProductFragment", "Gift "+ products.size() + "");
        }
        return products;
    }


}