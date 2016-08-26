package com.example.administrator.eightdayssample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.ActivityMonthlyHistoryBinding;

/**
 * Created by Administrator on 2016-08-26.
 */

public class MonthlyHistoryActivity extends AppCompatActivity{

    private ActivityMonthlyHistoryBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_monthly_history);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbar.setTitle("7월 사용 내역");
    }
}
