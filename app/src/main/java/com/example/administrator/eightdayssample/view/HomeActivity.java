package com.example.administrator.eightdayssample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-24.
 */

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding homeBinding;
//    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ImageView imgMenu;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        fragments = new ArrayList<>();
//        setViewPager();
        setTablayout();

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

//    private void setViewPager() {
//        viewPager = homeBinding.viewPager;
//        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new HistoryFragment(), "사용내역");
//        adapter.addFragment(new PayFragment(), "결제하기");
//        adapter.addFragment(new StoreFragment(), "가맹점");
//        viewPager.setAdapter(adapter);
//    }

    private void setTablayout() {
        fragments.add(new HistoryFragment());
        fragments.add(new PayFragment());
        fragments.add(new StoreFragment());

        tabLayout = homeBinding.tabs;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framContainer, fragments.get(tab.getPosition()));
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("사용내역"), true);
        tabLayout.addTab(tabLayout.newTab().setText("결제하기"));
        tabLayout.addTab(tabLayout.newTab().setText("가맹점"));
    }
}