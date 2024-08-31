package com.example.gbfindadmin.vendorMode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.databinding.ActivityAddItemBinding;
import com.example.gbfindadmin.vendorMode.models.ProductClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;
import java.util.UUID;

public class AddItemActivity extends AppCompatActivity {

    private ActivityAddItemBinding binding;
    private Uri imageUri;
    private FirebaseDatabase database;
    private DatabaseReference itemsRef;
    private DatabaseReference allItemsRef; // Reference for all items
    private FirebaseStorage firebaseStorage;
    private StorageReference storageRef;
    private ProgressDialog progressDialog;
    private String ownerName, ownerShopName, userId;

    private  DatabaseReference userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = FirebaseAuth.getInstance().getUid();
        userData = FirebaseDatabase.getInstance().getReference("VendorsDetail");
        ownerName = getIntent().getStringExtra("ownerName");
        ownerShopName = getIntent().getStringExtra("ownerShopName");
        userId = FirebaseAuth.getInstance().getUid();

        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference("images");
        database = FirebaseDatabase.getInstance();
        itemsRef = database.getReference("vendors");
        allItemsRef = database.getReference("allItems"); // Initialize the reference for all items

        progressDialog = new ProgressDialog(AddItemActivity.this);
        progressDialog.setMessage("Please wait\nAdding your item to store");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();

        binding.itemImage.setOnClickListener(v -> selectImage());

        binding.saveButton.setOnClickListener(v -> {
            // Check if the image is selected
            if (imageUri == null) {
                Toast.makeText(AddItemActivity.this, "Image is not selected", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the item name is empty
            if (binding.itemName.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddItemActivity.this, "Item name is required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the item price is empty
            if (binding.itemPrice.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddItemActivity.this, "Price is required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if a category is selected
            if (binding.categoryGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(AddItemActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.show();

            // If all validations pass, proceed with uploading the image
            final StorageReference reference = firebaseStorage.getReference()
                    .child("eulogyPictures")
                    .child(System.currentTimeMillis() + "_" + imageUri.getLastPathSegment());

            reference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                reference.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Proceed to upload product data
                    if (imageUri != null) {
                        uploadProductData(uri.toString());
                    }
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(AddItemActivity.this, "Failed to get image URL", Toast.LENGTH_LONG).show();
                });
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(AddItemActivity.this, "Image upload failed", Toast.LENGTH_LONG).show();
            });
        });
    }

    private void selectImage() {
        // Open gallery to pick an image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            binding.itemImage.setImageURI(imageUri);
        }
    }

    private void uploadProductData(String imageUri) {
        if (imageUri == null) {
            Toast.makeText(AddItemActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = UUID.randomUUID().toString();
        ProductClass product = new ProductClass(
                id,
                binding.itemName.getText().toString().trim(),
                binding.itemPrice.getText().toString().trim(),
                getSelectedCategory(),
                imageUri
        );

        String category = getSelectedCategory();

        // Save to 'allItems' reference
        allItemsRef.child(ownerShopName).child(id).setValue(product)
                .addOnSuccessListener(aVoid -> {
                    // After saving to 'allItems', save to the specific category
                    itemsRef.child(ownerShopName).child(category).child(id).setValue(product)
                            .addOnSuccessListener(aVoid1 -> {
                                progressDialog.dismiss();
                                finish();
                                Toast.makeText(AddItemActivity.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Log.e("UploadDebug", "Failed to upload product data to category: " + e.getMessage());
                                Toast.makeText(AddItemActivity.this, "Failed to upload product data to category: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Log.e("UploadDebug", "Failed to upload product data to allItems: " + e.getMessage());
                    Toast.makeText(AddItemActivity.this, "Failed to upload product data to allItems: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private String getSelectedCategory() {
        int selectedId = binding.categoryGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton.getText().toString();
    }

    private void fetchUserData(String userId) {

userData = database.getReference().child(userId);
        userData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                     ownerName = dataSnapshot.child("ownerName").getValue(String.class);
                     ownerShopName = dataSnapshot.child("ownerShopName").getValue(String.class);
                    String shopAddress = dataSnapshot.child("shopAddress").getValue(String.class);
                    String ownerContact = dataSnapshot.child("ownerContact").getValue(String.class);

                    // Handle the fetched details
//                    if (ownerName != null && ownerShopName != null) {
//                        // Use the fetched data as needed
//                        Toast.makeText(AddItemActivity.this, "Welcome " + ownerName, Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(AddItemActivity.this, DashboardActivity.class);
//                        intent.putExtra("ownerShopName", ownerShopName);
//                        intent.putExtra("ownerName", ownerName);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(AddItemActivity.this, "User details are incomplete", Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    startActivity(new Intent(AddItemActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddItemActivity.this, "Failed to load user details: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
