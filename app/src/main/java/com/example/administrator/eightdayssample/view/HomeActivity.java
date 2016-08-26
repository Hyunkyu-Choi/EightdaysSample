package com.example.administrator.eightdayssample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.adapter.HomeViewPagerAdapter;
import com.example.administrator.eightdayssample.databinding.ActivityHomeBinding;

/**
 * Created by Administrator on 2016-08-24.
 */

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding homeBinding;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ImageView imgMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setViewPager();

        TabLayout tabLayout = homeBinding.tabs;
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = homeBinding.drawerLayout;
        imgMenu = homeBinding.imgMenu;
        imgMenu.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void setViewPager() {
        viewPager = homeBinding.viewPager;
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HistoryFragment(), "사용내역");
        adapter.addFragment(new PayFragment(), "결제하기");
        adapter.addFragment(new StoreFragment(), "가맹점");
        viewPager.setAdapter(adapter);
    }
}