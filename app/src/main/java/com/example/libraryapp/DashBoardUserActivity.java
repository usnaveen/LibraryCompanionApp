package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.libraryapp.databinding.ActivityDashBoardUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoardUserActivity extends AppCompatActivity {

    private ActivityDashBoardUserBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        // Check if the user is already authenticated
        checkUser();

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign the user out
                firebaseAuth.signOut();
                // Redirect to the MainActivity
                startActivity(new Intent(DashBoardUserActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            // If no user is authenticated, redirect to MainActivity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // If a user is authenticated, set their email as the subtitle
            String email = firebaseUser.getEmail();
            binding.subTitleTv.setText(email);
        }
    }
}
