package com.example.gbfindadmin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.databinding.ActivityOrderShowBinding;

public class OrderShowActivity extends AppCompatActivity {

    private ActivityOrderShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}