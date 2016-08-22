package com.example.administrator.eightdayssample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.FragmentIntro1Binding;
import com.example.administrator.eightdayssample.databinding.FragmentIntro2Binding;
import com.example.administrator.eightdayssample.databinding.FragmentIntro3Binding;
import com.example.administrator.eightdayssample.databinding.FragmentLoginBinding;


/**
 * Created by Administrator on 2016-08-17.
 */

public class IntroFragment extends Fragment {

    public static final String PAGE = "page";
    private int page;
    private FragmentLoginBinding loginBinding;
    private FragmentIntro1Binding intro1Binding;
    private FragmentIntro2Binding intro2Binding;
    private FragmentIntro3Binding intro3Binding;

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
        View view;
        switch (page) {
            case 0:
                intro1Binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_intro1, container, false);
                view = intro1Binding.getRoot();
                break;
            case 1:
                intro2Binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_intro2, container, false);
                view = intro2Binding.getRoot();
                break;
            case 2:
                intro3Binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_intro3, container, false);
                view = intro3Binding.getRoot();
                break;
            default:
                loginBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_login, container, false);
                view = loginBinding.getRoot();
        }

        view.setTag(page);
        return view;
    }
}
