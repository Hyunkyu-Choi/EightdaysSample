package com.example.administrator.eightdayssample.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.ActivityChangePasswordBinding;
import com.example.administrator.eightdayssample.presenter.ChangePasswordPresenter;


/**
 * Created by Administrator on 2016-08-17.
 */

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordPresenter.View {

    private ActivityChangePasswordBinding changePasswordBinding;
    private Button btnChangePassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changePasswordBinding = DataBindingUtil.setContentView(this,R.layout.activity_change_password);

        btnChangePassword = changePasswordBinding.btnChangePassword;
        btnChangePassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });
    }
}
