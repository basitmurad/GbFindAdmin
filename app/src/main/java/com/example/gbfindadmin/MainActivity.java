
package com.example.gbfindadmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gbfindadmin.databinding.ActivityMainBinding;
import com.example.gbfindadmin.vendorMode.DashboardActivity;
import com.example.gbfindadmin.vendorMode.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){

            startActivity(new Intent(MainActivity.this ,DashboardActivity.class));
            Toast.makeText(this, "user is not null" +mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(MainActivity.this ,LoginActivity.class));

            Toast.makeText(this, "Please create an account", Toast.LENGTH_SHORT).show();
        }

    }

}
