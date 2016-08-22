package com.example.administrator.eightdayssample.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.administrator.eightdayssample.utils.AnalyticsApplication;
import com.example.administrator.eightdayssample.utils.ValidateUtils;
import com.example.administrator.eightdayssample.view.IntroActivity;
import com.google.android.gms.analytics.Tracker;


/**
 * Created by Administrator on 2016-08-17.
 */
public class IntroPresenter {

    private View view;
    private Context context;

    private Tracker tracker;

    public IntroPresenter(IntroActivity introActivity) {
        this.view = introActivity;
        this.context = introActivity;

        AnalyticsApplication application = (AnalyticsApplication) introActivity.getApplication();
        tracker = application.getDefaultTracker();
    }

    public void validateEmail(CharSequence email) {
        boolean isValid = ValidateUtils.isValidEmail(email);
        view.updateEtEmail(isValid);
    }

    public void isEmpty(CharSequence email, CharSequence password) {
        boolean isEmpty = !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
        view.showEmptyMessage(isEmpty);
    }

    public interface View {
        void updateIndicator(int position);

        void updateEtEmail(boolean isValid);

        void showEmptyMessage(boolean isEmpty);
    }
}
