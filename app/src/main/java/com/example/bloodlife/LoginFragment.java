package com.example.bloodlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlife.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment {
    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initUI();
        return view;

    }

    private void initUI() {
        binding.loginBT.setOnClickListener(v -> {
            navigateToMainActivity(getActivity());
        });
        binding.signupTV.setOnClickListener(v -> {
            replaceFragment(R.id.frameFL, new SignupFragment());
        });
    }


}