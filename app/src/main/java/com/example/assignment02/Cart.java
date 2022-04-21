package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.assignment02.Models.Product;
import com.example.assignment02.Util.ShoppingCart;
import com.example.assignment02.adapter.CartItemAdapter;
import com.example.assignment02.adapter.OnItemClickListener;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class Cart extends AppCompatActivity {
    private ShoppingCart cart;
    private RecyclerView recyclerView;
    private MaterialButton proceedButton;
    private TextView totalPrice;
    private TextView cartEmpty;
    private CartItemAdapter cartItemsAdapter;
    double totalAmount = 0;

    private void initComponent(){
        cart = ShoppingCart.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        totalPrice = findViewById(R.id.total_price);
        proceedButton = findViewById(R.id.submitButton);
        cartEmpty = findViewById(R.id.cartEmpty);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initComponent();
        proceedButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, Checkout.class);
            intent.putExtra("totalAmount", totalAmount);
            startActivity(intent);
        });
        displayItem();
    }

    private void displayItem(){
        List<Product> products = cart.getproducts();

        // if cart is empty, display the empty message
        if (products.size() == 0) {
            cartEmpty.setVisibility(View.VISIBLE);
            proceedButton.setEnabled(false);
        }
        //add all the price together
        for (Product current :
                products) {
            totalAmount += current.getPrice();
        }
        totalPrice.setText(String.format("$ %.2f", totalAmount));

        cartItemsAdapter = new CartItemAdapter(this, products, getApplicationContext().getTheme());
        cartItemsAdapter.setOnItemDeleteClickListener((view, product, pos) -> {
            totalAmount -= product.getPrice();
            totalPrice.setText(String.format("$ %.2f", totalAmount));
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(cartItemsAdapter);
    }
}
