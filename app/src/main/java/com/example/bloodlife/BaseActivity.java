package com.example.bloodlife;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {

    public void navigateToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    public void replaceFragment(int frameFL, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameFL, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
