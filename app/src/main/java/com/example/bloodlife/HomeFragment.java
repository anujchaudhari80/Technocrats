package com.example.bloodlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlife.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment {
    FragmentHomeBinding binding;
    HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initUI();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        homeAdapter = new HomeAdapter();
        binding.recyclerRVd.setAdapter(homeAdapter);

    }

    private void initUI() {

    }
}