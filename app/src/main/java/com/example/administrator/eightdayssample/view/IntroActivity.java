package com.example.administrator.eightdayssample.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.adapter.IntroPagerAdapter;
import com.example.administrator.eightdayssample.presenter.IntroPresenter;


public class IntroActivity extends AppCompatActivity implements IntroPresenter.View {

    private IntroPresenter introPresenter;

    private com.example.administrator.eightdayssample.IntroViewPager viewPager;
    private LinearLayout introIndicator;
    private EditText etEmail;
    private EditText etPassword;
    private ImageView btnClearEmail;
    private ImageView btnClearPassword;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_intro);

        introPresenter = new IntroPresenter(this);
        initViewpager();
    }

    private void initViewpager() {
        introIndicator = (LinearLayout) findViewById(R.id.introIndicator);

        updateIndicator(0);
        viewPager = (com.example.administrator.eightdayssample.IntroViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != IntroPagerAdapter.INTRO_SCREEN_MAX - 1)
                    updateIndicator(position);
                else {
                    introIndicator.setVisibility(View.INVISIBLE);
                    viewPager.setPagingDisabled();
                    initLoginPage();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initLoginPage() {
        //Initialize EditText clear button
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnClearEmail = (ImageView) findViewById(R.id.btnClearEmail);
        btnClearEmail.setOnClickListener(view -> etEmail.setText(""));

        etPassword = (EditText) findViewById(R.id.etPassword);
        btnClearPassword = (ImageView) findViewById(R.id.btnClearPassword);
        btnClearPassword.setOnClickListener(view -> etPassword.setText(""));

        //Check E-mail/password validation
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                introPresenter.validateEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(view -> introPresenter.isEmpty(etEmail.getText(), etPassword.getText()));
    }

    @Override
    public void updateIndicator(int position) {
        introIndicator.removeAllViews();

        ImageView[] dots = new ImageView[IntroPagerAdapter.INTRO_SCREEN_MAX - 1];
        for (int dotIndex = 0; dotIndex < dots.length; dotIndex++) {
            dots[dotIndex] = new ImageView(this);
            dots[dotIndex].setBackgroundResource(R.drawable.intro_dot_01);
            introIndicator.addView(dots[dotIndex]);
        }

        dots[position].setBackgroundResource(R.drawable.intro_dot_02);
    }

    @Override
    public void updateEtEmail(boolean isValid) {
        if (!isValid) {
            etEmail.setTextColor(Color.parseColor("#ff0000"));
            etEmail.setTag("invalid");
        } else {
            etEmail.setTextColor(Color.parseColor("#000000"));
            etEmail.setTag("valid");
        }
    }

    @Override
    public void showEmptyMessage(boolean isEmpty) {
        if (isEmpty && "valid".equals(etEmail.getTag())) {
            //Todo: 로그인 처리
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("이메일과 패스워드를 입력해주세요.")
                    .setPositiveButton("닫기", null);
            dialogBuilder.show();
        }
    }
}
