package com.example.administrator.eightdayssample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.eightdayssample.R;


/**
 * Created by Administrator on 2016-08-17.
 */

public class IntroFragment extends Fragment {

    public static final String PAGE = "page";
    private int page;

    public static IntroFragment newInstance(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE, page);
        IntroFragment introFragment = new IntroFragment();
        introFragment.setArguments(bundle);
        return introFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getArguments().containsKey(PAGE))
            Log.e("Error", "page 없음");
        page = getArguments().getInt(PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutID;
        switch (page) {
            case 0:
                layoutID = R.layout.screen_intro1;
                break;
            case 1:
                layoutID = R.layout.screen_intro2;
                break;
            case 2:
                layoutID = R.layout.screen_intro3;
                break;
            default:
                layoutID = R.layout.activity_login;
        }

        View view = getActivity().getLayoutInflater().inflate(layoutID, container, false);
        view.setTag(page);

        return view;
    }
}
