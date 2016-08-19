package com.example.administrator.eightdayssample.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Administrator on 2016-08-18.
 */
public class ValidateUtils {

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
