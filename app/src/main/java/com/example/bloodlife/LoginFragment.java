package com.example.bloodlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bloodlife.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends BaseFragment {
    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        binding.loginBT.setOnClickListener(v -> {
            if (!checkForEmptyFields()) {
                String email = binding.emailET.getText().toString().trim();
                String password = binding.passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getActivity(), "Authentication Successful.", Toast.LENGTH_SHORT).show();
                                navigateToMainActivity(getActivity());
                            } else {
                                Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.signupTV.setOnClickListener(v -> {
            replaceFragment(R.id.frameFL, new SignupFragment());
        });
    }

    private boolean checkForEmptyFields() {
        if (binding.emailET.getText().toString().trim().isEmpty()) {
            showToast("Enter cannot be empty");
        } else if (binding.passwordET.getText().toString().trim().isEmpty()) {
            showToast("Password cannot be empty");
        } else {
            return false;
        }
        return true;
    }
}