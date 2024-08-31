package com.example.gbfindadmin.vendorMode;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.databinding.ActivityCreateVendorBinding;

public class CreateVendorActivity extends AppCompatActivity {

    private ActivityCreateVendorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateVendorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}