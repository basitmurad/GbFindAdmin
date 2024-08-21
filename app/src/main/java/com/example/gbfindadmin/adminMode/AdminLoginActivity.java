package com.example.gbfindadmin.adminMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.databinding.ActivityAdminLoginBinding;

public class AdminLoginActivity extends AppCompatActivity {

    ActivityAdminLoginBinding binding;
    private static final String PREDEFINED_EMAIL = "admin@example.com";
    private static final String PREDEFINED_PASSWORD = "password123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginActivity.this , AdminDashboardActivity.class));

//                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (email.equals(PREDEFINED_EMAIL) && password.equals(PREDEFINED_PASSWORD)) {
            // Successful login

            startActivity(new Intent(AdminLoginActivity.this , AdminDashboardActivity.class));
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            // Proceed to the next activity or screen
        } else {
            // Failed login
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}