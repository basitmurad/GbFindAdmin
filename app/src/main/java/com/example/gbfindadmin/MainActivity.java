package com.example.gbfindadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.adminMode.AdminLoginActivity;
import com.example.gbfindadmin.databinding.ActivityMainBinding;
import com.example.gbfindadmin.vendorMode.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



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

    private void navigateToAdminLogin() {
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }

    private void navigateToVendorLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}