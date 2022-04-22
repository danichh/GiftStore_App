// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.assignment02.Util.Util;
import com.example.assignment02.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * Initial View component
     */
    private void initComponent(){
        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    /**
     * add a specific menu to the menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * generate the viewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ProductFragment.newInstance(Util.GIFT_CLASS), "Gifts");
        adapter.addFragment(ProductFragment.newInstance(Util.FLOWER_CLASS), "Flowers");
        adapter.addFragment(ProductFragment.newInstance("favorites"), "Favorites");
        viewPager.setAdapter(adapter);
    }


    /**
     *
     * @param item is the menu item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // if the cart buttom(menu item) is click send to cart
        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
