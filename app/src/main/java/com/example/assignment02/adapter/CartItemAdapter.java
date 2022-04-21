package com.example.assignment02.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment02.Models.Product;
import com.example.assignment02.R;
import com.example.assignment02.Util.ShoppingCart;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private List<Product> products;
    private Resources.Theme theme;
    private ShoppingCart cart;

    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemDeleteClickListener(final OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }

    public CartItemAdapter(Context context, List<Product> items, Resources.Theme theme) {
        this.products = items;
        this.context = context;
        this.theme = theme;
        cart = ShoppingCart.getInstance();
    }



    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(String.format("$ %.2f", product.getPrice()));
        holder.image.setImageDrawable(context.getResources().getDrawable(product.getImage(), theme));
        holder.delete_button.setOnClickListener(view -> {
            onItemClickListener.onItemClick(view, products.get(position), position);
            cart.removeFromCart(product);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, products.size());
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView price;
        public ImageButton delete_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            delete_button = itemView.findViewById(R.id.delete_button);
        }
    }
}
