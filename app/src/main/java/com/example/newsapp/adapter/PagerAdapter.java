package com.example.newsapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.fragmnet.ArticlesFragment;
import com.example.newsapp.fragmnet.BusinessNewsFragment;
import com.example.newsapp.fragmnet.TechNewsFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    private int totalTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int totalTabs) {
        super(fm, totalTabs);
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                return fragment = new BusinessNewsFragment();
            case 1:
                return fragment = new TechNewsFragment();
            case 2:
                return fragment = new ArticlesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
