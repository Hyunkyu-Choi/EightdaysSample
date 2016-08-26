package com.example.administrator.eightdayssample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-24.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;
    private final List<String> fragmentTitles;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
