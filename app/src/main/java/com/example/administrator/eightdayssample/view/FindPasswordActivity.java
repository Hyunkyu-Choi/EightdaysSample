package com.example.administrator.eightdayssample.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.ActivityFindPasswordBinding;
import com.example.administrator.eightdayssample.presenter.FindPasswordPresenter;

/**
 * Created by Administrator on 2016-08-17.
 */
public class FindPasswordActivity extends AppCompatActivity implements FindPasswordPresenter.View {

    private ActivityFindPasswordBinding findPasswordBinding;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_password);

        toolbar = (Toolbar) findPasswordBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("비밀번호 찾기");
        toolbar.setNavigationOnClickListener(view -> {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        });
    }
}
