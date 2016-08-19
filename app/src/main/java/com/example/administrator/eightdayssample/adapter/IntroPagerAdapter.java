package com.example.administrator.eightdayssample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.eightdayssample.view.IntroFragment;

/**
 * Created by Administrator on 2016-08-17.
 */

public class IntroPagerAdapter extends FragmentPagerAdapter {
    public static final int INTRO_SCREEN_MAX = 4;

    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return INTRO_SCREEN_MAX;
    }
}
