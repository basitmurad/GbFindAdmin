package com.example.gbfindadmin.vendorMode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gbfindadmin.MainActivity;
import com.example.gbfindadmin.R;
import com.example.gbfindadmin.databinding.ActivityDashboardBinding;
import com.example.gbfindadmin.vendorMode.fragments.MyFragmentStateAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference userDetailRef;
    private String ownerShopName, ownerName;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton navIcon;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_USER_ROLE = "userRole";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        userDetailRef = FirebaseDatabase.getInstance().getReference("VendorsDetail"); // Adjust path as needed

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

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.nav_home) {
//
//
//                    // Handle the home action
//                } else if (item.getItemId() == R.id.nav_admin) {
//
//                    Intent homeIntent = new Intent(DashboardActivity.this, AdminLoginActivity.class);
//
//                    updateUserRoleAndNavigate();
//                    startActivity(homeIntent);
//                    // Handle the settings action
//                } else {
//                    Intent homeIntent = new Intent(DashboardActivity.this, AdminLoginActivity.class);
//                    startActivity(homeIntent);
//                    finishAffinity();
//
//                    // Handle other menu items
//                }
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//
//
//            }
//        });
        checkUserAndFetchData();

        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(adapter);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Food"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cloths"));

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }




    public void NavigateToAddItem(View view) {
        Intent intent = new Intent(DashboardActivity.this, AddItemActivity.class);


        intent.putExtra("ownerName", ownerName);
        intent.putExtra("ownerShopName", ownerShopName);
        startActivity(intent);

    }

    private void checkUserAndFetchData() {
        String currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        // Query the database for the specific user
        userDetailRef.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data exists
                    String adminFcm = dataSnapshot.child("adminFcm").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String ownerContact = dataSnapshot.child("ownerContact").getValue(String.class);
                    ownerName = dataSnapshot.child("ownerName").getValue(String.class);
                    ownerShopName = dataSnapshot.child("ownerShopName").getValue(String.class);
                    String shopAddress = dataSnapshot.child("shopAddress").getValue(String.class);

                    // Log or use the retrieved data as needed
                    Log.d("UserData", "Email: " + email);
                    Log.d("UserData", "Owner Name: " + ownerName);
                    Log.d("UserData", "Owner Shop Name: " + ownerShopName);

                    // Use the retrieved data for further processing
                } else {
                    // User data does not exist
                    Toast.makeText(DashboardActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
                Log.e("DatabaseError", "Error: " + databaseError.getMessage());
            }
        });
    }
    private void updateUserRoleAndNavigate() {
        String currentUserId = mAuth.getCurrentUser().getUid();

        // Update user role in Firebase
        userDetailRef.child(currentUserId).child("role").setValue("admin").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Save role in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USER_ROLE, "admin");
                editor.apply();

                // Navigate to AdminLoginActivity
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            } else {
                // Handle errors
                Toast.makeText(DashboardActivity.this, "Failed to update role", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void checkUserStatus() {

    }

}