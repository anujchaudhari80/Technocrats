package com.example.bloodlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlife.databinding.FragmentNotificationBinding;

public class BloodAvailability extends BaseFragment {
    FragmentNotificationBinding binding;
    BloodAvailaibilityAdapter bloodAvailaibilityAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initUI();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        bloodAvailaibilityAdapter = new BloodAvailaibilityAdapter();
        binding.recyclerRVd.setAdapter(bloodAvailaibilityAdapter);

    }

    private void initUI() {

    }
}