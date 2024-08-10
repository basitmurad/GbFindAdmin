package com.example.gbfindadmin.vendorMode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gbfindadmin.R;
import com.example.gbfindadmin.databinding.ActivityAddItemBinding;

public class AddItemActivity extends AppCompatActivity {

    private ActivityAddItemBinding binding;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

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
    }}