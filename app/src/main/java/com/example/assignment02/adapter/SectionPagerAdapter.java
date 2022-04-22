// --------------------------------------------------------------------
// Assignment 2
// Written by: Danich Hang , 1951307
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package com.example.assignment02.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the fragments view
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titres = new ArrayList<>();

    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    /**
     * add the fragment and the titre to the arrayLists
     * @param fragment
     * @param titre
     */
    public void addFragment(Fragment fragment, String titre){
        fragments.add(fragment);
        titres.add(titre);
    }

    /**
     * retrive the Fragment base on  the position
     * @param position is the given position
     * @return the Fragment
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * retrive the titre base on  the position
     * @param position is the given position
     * @return the titre
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titres.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
