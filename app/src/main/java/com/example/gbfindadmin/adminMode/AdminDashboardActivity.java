package com.example.gbfindadmin.adminMode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbfindadmin.MainActivity;
import com.example.gbfindadmin.R;
import com.example.gbfindadmin.adminMode.adapter.UserAdapter;
import com.example.gbfindadmin.databinding.ActivityAdminDashboardBinding;
import com.example.gbfindadmin.vendorMode.DashboardActivity;
import com.example.gbfindadmin.vendorMode.models.UserClass;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    ActivityAdminDashboardBinding binding;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton navIcon;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    private DatabaseReference userDetailRef;
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_USER_ROLE = "userRole";
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<UserClass> userList;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.vendorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("UserDetail");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserClass user = snapshot.getValue(UserClass.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminDashboardActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        userDetailRef = FirebaseDatabase.getInstance().getReference("UserDetail"); // Adjust path as needed

        navIcon = findViewById(R.id.nav_icon);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up the icon click listener
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {


                    // Handle the home action
                } else if (item.getItemId() == R.id.nav_admin) {

                    Intent homeIntent = new Intent(AdminDashboardActivity.this, AdminDashboardActivity.class);

                    updateUserRoleAndNavigate();
                    startActivity(homeIntent);
                    // Handle the settings action
                } else {
                    Intent homeIntent = new Intent(AdminDashboardActivity.this, AdminDashboardActivity.class);
                    startActivity(homeIntent);
                    finishAffinity();

                    // Handle other menu items
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });
    }

    private void updateUserRoleAndNavigate() {
        String currentUserId = mAuth.getCurrentUser().getUid();

        // Update user role in Firebase
        userDetailRef.child(currentUserId).child("role").setValue("user").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Save role in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USER_ROLE, "user");
                editor.apply();

                // Navigate to AdminLoginActivity
                Intent intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            } else {
                // Handle errors
                Toast.makeText(AdminDashboardActivity.this, "Failed to update role", Toast.LENGTH_SHORT).show();
            }
        });
    }

}