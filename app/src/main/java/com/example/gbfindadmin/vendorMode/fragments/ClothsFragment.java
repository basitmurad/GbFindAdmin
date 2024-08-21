package com.example.gbfindadmin.vendorMode.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.vendorMode.models.ProductClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClothsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference userDetailRef;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<ProductClass> productList;
    private DatabaseReference productRef;
    private String ownerName, ownerShopName;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cloths, container, false);

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Fetching items");
        progressDialog.setCancelable(false);


        mAuth = FirebaseAuth.getInstance();
        userDetailRef = FirebaseDatabase.getInstance().getReference("UserDetail");

        recyclerView = view.findViewById(R.id.vendorRecycler22222);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialog.show();

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this::deleteProduct);
        recyclerView.setAdapter(productAdapter);

        checkUserAndFetchData();

        return view;
    }

    private void checkUserAndFetchData() {

        String currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        userDetailRef.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data exists
                    ownerName = dataSnapshot.child("ownerName").getValue(String.class);
                    ownerShopName = dataSnapshot.child("ownerShopName").getValue(String.class);

                    Log.d("UserData", "Owner Name: " + ownerName);
                    Log.d("UserData", "Owner Shop Name: " + ownerShopName);

                    // Initialize productRef and fetch products
                    if (ownerShopName != null) {
                        productRef = FirebaseDatabase.getInstance().getReference("vendors").child(ownerShopName).child("Clothing");
                        fetchProducts();
                    } else {
                        Log.e("FoodFragment", "Owner shop name is null");
                    }
                } else {
                    progressDialog.dismiss();
                    // User data does not exist
                    Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                // Handle possible errors.
                Log.e("DatabaseError", "Error: " + databaseError.getMessage());
            }
        });
    }

    private void fetchProducts() {
        if (productRef != null) {
            productRef.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    productList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ProductClass product = snapshot.getValue(ProductClass.class);
                        productList.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    progressDialog.dismiss();
                    // Handle possible errors.
                    Log.e("DatabaseError", "Error: " + databaseError.getMessage());
                }
            });
        } else {
            progressDialog.dismiss();
            Log.e("FoodFragment", "Product reference is null");
        }
    }

    private void deleteProduct(ProductClass product) {
        if (productRef != null) {
            productRef.child(product.getId()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        // Optionally show a toast or update UI
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                        Log.e("DeleteError", "Failed to delete product: " + e.getMessage());
                    });
        } else {
            Log.e("FoodFragment", "Product reference is null");
        }
    }
}
