package com.example.bloodlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlife.databinding.FragmentHomeBinding;
import com.example.bloodlife.databinding.FragmentNotificationBinding;

public class NotificationFragment extends BaseFragment {
FragmentNotificationBinding binding;
NotificationAdapter notificationAdapter;
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
        notificationAdapter = new NotificationAdapter();
        binding.recyclerRVd.setAdapter(notificationAdapter);

    }

    private void initUI() {

    }
}