package com.example.administrator.eightdayssample.utils;

import android.app.Application;

import com.example.administrator.eightdayssample.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Administrator on 2016-08-19.
 */

public class AnalyticsApplication extends Application {

    private Tracker tracker;

    synchronized public Tracker getDefaultTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

            tracker = analytics.newTracker(R.xml.global_tracker);
        }
        return tracker;
    }
}
