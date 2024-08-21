//package com.example.gbfindadmin.vendorMode;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.gbfindadmin.databinding.ActivitySignUpBinding;
//import com.example.gbfindadmin.vendorMode.models.UserClass;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SignUpActivity extends AppCompatActivity {
//
//    ActivitySignUpBinding binding;
//    private ProgressDialog progressDialog;
//    private FirebaseAuth firebaseAuth;
//    private DatabaseReference databaseReference;
//
//    private String ownerEmail , ownerPassword , ownerName ,ownerShopName ,ownerShopLocation ,ownerContactNumber;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        progressDialog = new ProgressDialog(this);
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
////        fcmToken();
//        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckValidations();
//            }
//        });
//
//
//
//
//    }
//
//
//
//    private void CheckValidations() {
//        if (binding.etEmail.getText().toString().isEmpty()) {
//            binding.etEmail.setError("Name is empty");
//        }
//        else if (binding.etPassword.getText().toString().isEmpty()) {
//            binding.etPassword.setError("Password is missing");
//        }
//        else if (binding.etOwnerName.getText().toString().isEmpty()) {
//            binding.etOwnerName.setError("owner name is empty");
//        }
//        else if (binding.etShopName.getText().toString().isEmpty()) {
//
//            binding.etShopName.setError("Email is empty");
//        }
//        else if (binding.etContactNumber.getText().toString().isEmpty()) {
//
//            binding.etShopName.setError("number is empty");
//        }
//
//
//        else if (binding.etVendorLocation.getText().toString().isEmpty()) {
//
//            binding.etShopName.setError("location is empty");
//        }
//        else {
//
//
//            progressDialog.setTitle("PLease wait..");
//            progressDialog.setMessage("Creating account");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//
//            ownerEmail = binding.etEmail.getText().toString();
//            ownerPassword = binding.etEmail.getText().toString();
//            ownerEmail = binding.etEmail.getText().toString();
//            ownerEmail = binding.etEmail.getText().toString();
//            ownerEmail = binding.etEmail.getText().toString();
//            ownerEmail = binding.etEmail.getText().toString();
//
//
//          createAccountWithEmailAndPassword(ownerEmail ,ownerPassword);
//
//
//
//
//        }
//    }
//
//    private void createAccountWithEmailAndPassword(String email, String password) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            progressDialog.dismiss();
//                            userUId = FirebaseAuth.getInstance().getCurrentUser().getUid();
////                            SendDataToFireBase();
////                            sessionManager.saveCredentials(name, password, email, number);
//                            Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
//
//                        } else {
//
//                            progressDialog.dismiss();
//
//                            Toast.makeText(SignUpActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    private void SendDataToFireBase() {
//        Users users = new Users(name, email, number, password, userUId,adminFcmToken);
//        databaseReference.child("AdminDetail").child(userUId).setValue(users)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                        progressDialog.dismiss();
//                        Intent intent = new Intent(RegistrationActivity.this, DashboardActivity.class);
//
//
//                        startActivity(intent);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
//                        Toast.makeText(RegistrationActivity.this, "Try Again...\n something went wrong", Toast.LENGTH_SHORT).show();
//
//                        Toast.makeText(RegistrationActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//    }
////    private void fcmToken() {
////        FirebaseMessaging.getInstance().getToken()
////                .addOnCompleteListener(task -> {
////                    if (!task.isSuccessful()) {
////                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
////                        return;
////                    }
////
////                    // Get the FCM token
////                    adminFcmToken = task.getResult();
////                    sessionManager.setAdminFcmToken(adminFcmToken);
////
////                    // Now you have the FCM token for the admin app
////                    Log.d(TAG, "token" + adminFcmToken);
////                    Log.d("toeken" , adminFcmToken);
//////                    Toast.makeText(this, ""+adminFcmToken, Toast.LENGTH_SHORT).show();
////                    // Save the FCM token to your server or preferences if needed
////                });
////    }
//
//}

package com.example.gbfindadmin.vendorMode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gbfindadmin.databinding.ActivitySignUpBinding;
import com.example.gbfindadmin.vendorMode.models.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private String ownerEmail, ownerPassword, ownerName, ownerShopName, ownerShopLocation, ownerContactNumber ,adminFcmToken ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        fcmToken();
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidations();
            }
        });
    }

    private void CheckValidations() {
        if (binding.etEmail.getText().toString().isEmpty()) {
            binding.etEmail.setError("Email is empty");
        } else if (binding.etPassword.getText().toString().isEmpty()) {
            binding.etPassword.setError("Password is missing");
        } else if (binding.etOwnerName.getText().toString().isEmpty()) {
            binding.etOwnerName.setError("Owner name is empty");
        } else if (binding.etShopName.getText().toString().isEmpty()) {
            binding.etShopName.setError("Shop name is empty");
        } else if (binding.etContactNumber.getText().toString().isEmpty()) {
            binding.etContactNumber.setError("Contact number is empty");
        } else if (binding.etVendorLocation.getText().toString().isEmpty()) {
            binding.etVendorLocation.setError("Location is empty");
        } else {
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Creating account");
            progressDialog.setCancelable(false);
            progressDialog.show();

            ownerEmail = binding.etEmail.getText().toString();
            ownerPassword = binding.etPassword.getText().toString();
            ownerName = binding.etOwnerName.getText().toString();
            ownerShopName = binding.etShopName.getText().toString();
            ownerContactNumber = binding.etContactNumber.getText().toString();
            ownerShopLocation = binding.etVendorLocation.getText().toString();

            createAccountWithEmailAndPassword(ownerEmail, ownerPassword);
        }
    }

    private void createAccountWithEmailAndPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            String userUId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            SendDataToFireBase(userUId);
                            Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SendDataToFireBase(String userUId) {

UserClass userClass = new UserClass(ownerEmail ,ownerName ,ownerShopName,ownerPassword, ownerShopLocation ,ownerContactNumber ,adminFcmToken);
        databaseReference.child("UserDetail").child(userUId).setValue(userClass)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Try Again...\nSomething went wrong", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void fcmToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get the FCM token
                    adminFcmToken = task.getResult();

                    // Now you have the FCM token for the admin app
                    Log.d("TAG", "token" + adminFcmToken);
                    Log.d("toeken" , adminFcmToken);
//                    Toast.makeText(this, ""+adminFcmToken, Toast.LENGTH_SHORT).show();
                    // Save the FCM token to your server or preferences if needed
                });
    }

}
