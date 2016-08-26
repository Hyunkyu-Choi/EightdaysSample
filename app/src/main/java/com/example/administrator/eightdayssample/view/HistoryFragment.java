package com.example.administrator.eightdayssample.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.FragmentHistoryBinding;

/**
 * Created by Administrator on 2016-08-24.
 */

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container,false);

        binding.llMonthlyHistory.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MonthlyHistoryActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return binding.getRoot();
    }
}
