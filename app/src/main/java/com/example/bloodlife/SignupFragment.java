package com.example.bloodlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bloodlife.databinding.FragmentHomeBinding;
import com.example.bloodlife.databinding.FragmentSignupBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class SignupFragment extends BaseFragment {
    private FragmentSignupBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        FirebaseApp.initializeApp(getActivity());
        initUI();
        return view;
    }

    private void initUI() {
        mAuth = FirebaseAuth.getInstance();
        binding.signInBT.setOnClickListener(v ->{
            signUp(binding.emailET.getText().toString().trim(),binding.passwordET.getText().toString(),binding.nameET.toString().trim(),binding.locationET.toString().trim(),binding.bloodTypeET.toString().trim(),binding.phoneNumberET.toString().trim());
        });
    }

    private void signUp(String email, String password, String name, String location, String bloodType,String phoneNumber) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid();

                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        DatabaseReference userProfileRef = usersRef.child(userId);

                        // Store user profile information
                        userProfileRef.child("name").setValue(name);
                        userProfileRef.child("location").setValue(location);
                        userProfileRef.child("bloodType").setValue(bloodType);
                        userProfileRef.child("phoneNumber").setValue(phoneNumber);
                        Toast.makeText(getActivity(), "Signed up successfully!", Toast.LENGTH_SHORT).show();
                        replaceFragment(R.id.frameFL, new LoginFragment());

                        // Continue with any other necessary operations or UI updates
                    } else {
                        // Sign-up failed, display a message to the user
                       Toast.makeText(getActivity(), "Sign-up failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}