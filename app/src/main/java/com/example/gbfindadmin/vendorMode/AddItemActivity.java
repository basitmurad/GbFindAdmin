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
    private FirebaseStorage firebaseStorage;
    private StorageReference storageRef;
    private DatabaseReference userDetailRef;
    String adminFcm, email ,ownerContact ,ownerName ,ownerShopName ,shopAddress;
    String userId;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ownerName = getIntent().getStringExtra("ownerName");
        Log.d("name" ,ownerName);
        ownerShopName = getIntent().getStringExtra("ownerShopName");
        Log.d("name" ,ownerShopName);
        userId = FirebaseAuth.getInstance().getUid();

        // Fetch the user details

        storageRef = FirebaseStorage.getInstance().getReference("images");
        firebaseStorage = FirebaseStorage.getInstance();

        database = FirebaseDatabase.getInstance();
        itemsRef = database.getReference("vendors");

        progressDialog = new ProgressDialog(AddItemActivity.this);
        progressDialog.setMessage("Please wait\nAdding your item to store");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();

        binding.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // Check if the image was successfully uploaded and then call uploadImage
                                if (imageUri != null) {
                                    uploadImage(imageUri.toString());
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(AddItemActivity.this, "Failed to get image URL", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AddItemActivity.this, "Image upload failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
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
    private void uploadImage(String imageUri) {
        if (imageUri == null) {
            Toast.makeText(AddItemActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log the URI and storage reference path
        Log.d("UploadDebug", "Image URI: " + imageUri);
        StorageReference fileReference = storageRef.child(System.currentTimeMillis() + ".jpg");
        Log.d("UploadDebug", "Uploading file to: " + fileReference.getPath());

        String id = UUID.randomUUID().toString();
        // Upload the image to Firebase Storage
        UploadTask uploadTask = fileReference.putFile(Uri.parse(imageUri));
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Get the download URL for the uploaded image
            fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                Log.d("UploadDebug", "Image uploaded successfully. URL: " + downloadUrl);

                // After getting the download URL, upload the product details to Firebase Realtime Database
//                String productId = itemsRef.push().getKey(); // Generate a unique key for each product
                ProductClass product = new ProductClass(
                        id,
                        binding.itemName.getText().toString().trim(),
                        binding.itemPrice.getText().toString().trim(),
                        getSelectedCategory(),
                        downloadUrl
                );

                String cat = getSelectedCategory();

                // Upload the product data
                itemsRef.child(ownerShopName).child(cat).child(id).setValue(product)
                        .addOnSuccessListener(aVoid -> {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(AddItemActivity.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Log.e("UploadDebug", "Failed to upload product data: " + e.getMessage());
                            Toast.makeText(AddItemActivity.this, "Failed to upload product data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });

            }).addOnFailureListener(e -> {

                progressDialog.dismiss();
                Log.e("UploadDebug", "Failed to get download URL: " + e.getMessage());
                Toast.makeText(AddItemActivity.this, "Failed to get download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Log.e("UploadDebug", "Upload failed: " + e.getMessage());
            Toast.makeText(AddItemActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private String getSelectedCategory() {
        int selectedId = binding.categoryGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton.getText().toString();
    }



}
