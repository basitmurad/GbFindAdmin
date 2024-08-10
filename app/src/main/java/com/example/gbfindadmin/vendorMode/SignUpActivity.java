package com.example.gbfindadmin.vendorMode;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gbfindadmin.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding =ActivitySignUpBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user input from binding
                String userName = binding.etUserName.getText().toString().trim();
                String shopName = binding.etShopName.getText().toString().trim();
                String contactNumber = binding.etContactNumber.getText().toString().trim();

                // Validate the inputs
                if (userName.isEmpty() || shopName.isEmpty() || contactNumber.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle sign-up logic (e.g., save the data, navigate to another screen)
                    Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                    // TODO: Add further sign-up logic here
                }
            }
        });
    }
}