package com.example.fooddoor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminChangePasswordActivity extends AppCompatActivity {

    EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    Button btnUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_password);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarAdminChangePassword);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Views
        etCurrentPassword = findViewById(R.id.etAdminCurrentPassword);
        etNewPassword = findViewById(R.id.etAdminNewPassword);
        etConfirmPassword = findViewById(R.id.etAdminConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnAdminUpdatePassword);

        btnUpdatePassword.setOnClickListener(v -> validateAndUpdate());
    }

    private void validateAndUpdate() {
        String current = etCurrentPassword.getText().toString().trim();
        String newPass = etNewPassword.getText().toString().trim();
        String confirm = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(current)) {
            etCurrentPassword.setError("Current password required");
            return;
        }

        if (TextUtils.isEmpty(newPass)) {
            etNewPassword.setError("New password required");
            return;
        }

        if (newPass.length() < 6) {
            etNewPassword.setError("Minimum 6 characters");
            return;
        }

        if (!newPass.equals(confirm)) {
            etConfirmPassword.setError("Passwords do not match");
            return;
        }

        // TODO: API call / Firebase / Backend validation
        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}