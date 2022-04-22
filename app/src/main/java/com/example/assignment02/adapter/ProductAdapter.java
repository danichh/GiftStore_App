// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
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
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment02.Models.Product;
import com.example.assignment02.R;
import com.example.assignment02.Util.DatabaseHandler;
import com.example.assignment02.Util.ShoppingCart;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Resources.Theme theme;
    private Context context;
    private List<Product> products;
    private ShoppingCart shoppingCart;
    private DatabaseHandler db;

    private OnMoreButtonClickListener onMoreButtonClickListener;

    public ProductAdapter(Resources.Theme theme, Context context, List<Product> products) {
        this.theme = theme;
        this.context = context;
        this.products = products;
        db = new DatabaseHandler(context);
        shoppingCart = ShoppingCart.getInstance();
    }

    // method to set the moreButton is click
    public void setOnMoreButtonClickListener(final OnMoreButtonClickListener onMoreButtonClickListener) {
        this.onMoreButtonClickListener = onMoreButtonClickListener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(String.format("$ %.2f", product.getPrice()));
        holder.image.setImageDrawable(context.getResources().getDrawable(product.getImage(), theme));

        // change the image of the like button, 1 is full , 0 is empty
        holder.favorite.setImageResource(product.getFavorite() > 0 ? R.drawable.ic_baseline_select_favorite_24 : R.drawable.ic_baseline_favorite_border_24 );
        holder.favorite.setOnClickListener(view -> { // when the favorite is click
            if(product.getFavorite() == 0){ // change the value to the opposites value ex: fav = 0; fav = 1
                db.updateFavorite(product.getId(), 1);
                product.setFavorite(1);
            } else if (product.getFavorite() == 1){
                db.updateFavorite(product.getId(), 0);
                product.setFavorite(0);
            }
            notifyItemChanged(position);
        });

        holder.more.setOnClickListener(view -> {
            onMoreButtonClick(view, product);
        });

        holder.cart.setOnClickListener(view -> {
            shoppingCart.addItemToCart(product);
            Snackbar.make(view, product.getTitle()  + " added to cart", Snackbar.LENGTH_SHORT).show();
        });
    }

    /**
     *
     * When the more buttom is click, inflate the product menu and set the menu clicked
     */
    private void onMoreButtonClick(final View view, final Product p) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.setOnMenuItemClickListener(item -> {
            onMoreButtonClickListener.onItemClick(view, p, item);
            return true;
        });
        popupMenu.inflate(R.menu.product_menu);
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * Sets new products to the adopter
     * @param items the products
     */
    public void setItems(List<Product> items) {
        this.products.clear();
        this.products.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView price;
        public ImageButton more;
        public ImageButton favorite;
        public ImageButton cart;
        public View lyt_parent;

        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            title = v.findViewById(R.id.title);
            price = v.findViewById(R.id.price);
            more = v.findViewById(R.id.more);
            favorite = v.findViewById(R.id.favorite);
            cart = v.findViewById(R.id.cart);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }
}
