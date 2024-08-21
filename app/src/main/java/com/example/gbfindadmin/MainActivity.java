
package com.example.gbfindadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gbfindadmin.adminMode.AdminLoginActivity;
import com.example.gbfindadmin.databinding.ActivityMainBinding;
import com.example.gbfindadmin.vendorMode.DashboardActivity;
import com.example.gbfindadmin.vendorMode.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_USER_ROLE = "userRole";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();

        // Check if the user is already logged in
        if (mAuth.getCurrentUser() != null) {

            navigateToDashboard1();
            // Redirect to the dashboard
//            navigateToDashboard();
        } else {
            // Show the login options
            binding.btnAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigateToAdminLogin();
                }
            });

            binding.btnVendor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigateToVendorLogin();
                }
            });
        }
    }

    private void navigateToAdminLogin() {
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }

    private void navigateToVendorLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void navigateToDashboard() {
        // Assuming you have a DashboardActivity
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish(); // Optional: finish MainActivity to prevent going back to it
    }

    private void navigateToDashboard1() {
        // Retrieve the user role from SharedPreferences
        String userRole = sharedPreferences.getString(KEY_USER_ROLE, "vendor");

        Intent intent;
        if ("admin".equals(userRole)) {
            // Redirect to the Admin Dashboard
            intent = new Intent(MainActivity.this, AdminLoginActivity.class); // Replace with actual Admin Dashboard Activity
        } else {
            // Redirect to the Vendor Dashboard
            intent = new Intent(MainActivity.this, DashboardActivity.class);
        }
        startActivity(intent);
        finish(); // Optional: finish MainActivity to prevent going back to it
    }
}
