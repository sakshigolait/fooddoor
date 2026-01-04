package com.example.fooddoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminProfileActivity extends AppCompatActivity {

    LinearLayout optionEditProfile, optionNotification, optionPassword,optionaboutus;
    Button btnSignOut;
    ImageView profileImageEdit,imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        // Profile image edit icon (camera / pencil)
        profileImageEdit = findViewById(R.id.btnEditProfileImage);

        // Options
        optionEditProfile = findViewById(R.id.optionEditProfile);
        optionNotification = findViewById(R.id.optionNotification);
        imgProfile = findViewById(R.id.imgProfile);

        optionPassword = findViewById(R.id.optionPassword);
        optionaboutus = findViewById(R.id.optionaboutus);

        btnSignOut = findViewById(R.id.btnSignOut);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityResultLauncher<Intent> galleryLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                Uri imageUri = result.getData().getData();
                                imgProfile.setImageURI(imageUri);
                            }
                        });

// Enable back arrow
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

// Back arrow click
        toolbar.setNavigationOnClickListener(v -> finish());
        ((TextView) optionEditProfile.findViewById(R.id.txtOptionTitle))
                .setText("Edit Profile");

        ((ImageView) optionEditProfile.findViewById(R.id.imgOptionIcon))
                .setImageResource(R.drawable.icon_edit);

        ((TextView) optionNotification.findViewById(R.id.txtOptionTitle))
                .setText("Notification");

        ((ImageView) optionNotification.findViewById(R.id.imgOptionIcon))
                .setImageResource(R.drawable.icon_notifications);

        ((TextView) optionPassword.findViewById(R.id.txtOptionTitle))
                .setText("Change Password");
        ((ImageView) optionPassword.findViewById(R.id.imgOptionIcon))
                .setImageResource(R.drawable.icon_lock);

        ((TextView) optionaboutus.findViewById(R.id.txtOptionTitle))
                .setText("Edit Aboutus");
        ((ImageView) optionaboutus.findViewById(R.id.imgOptionIcon))
                .setImageResource(R.drawable.icon_aboutus);

        // Open Edit Profile
        optionEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(AdminProfileActivity.this, AdminEditProfileActivity.class);
            startActivity(intent);
        });
        optionNotification.setOnClickListener(v ->
                startActivity(new Intent(this, AdminNotificationActivity.class)));

        optionPassword.setOnClickListener(v ->
                startActivity(new Intent(this, AdminChangePasswordActivity.class)));

        optionaboutus.setOnClickListener(v ->
                startActivity(new Intent(this, AdminAboutUsActivity.class)));
        // Sign out
        btnSignOut.setOnClickListener(v -> {
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();

            finish();
        });

        // Change profile image
        profileImageEdit.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

    }
}