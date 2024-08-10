package com.example.gbfindadmin.vendorMode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gbfindadmin.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}