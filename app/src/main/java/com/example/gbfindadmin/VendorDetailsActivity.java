package com.example.gbfindadmin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.databinding.ActivityVendorDetailsBinding;

public class VendorDetailsActivity extends AppCompatActivity {

    private ActivityVendorDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVendorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());









    }
}