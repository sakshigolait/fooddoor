package com.example.fooddoor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SignupActivity extends AppCompatActivity {

    EditText srname, srnumber, sremailid, srpassword;
    Button btnsubmit;

    // Role buttons
    Button btnUser, btnAdmin, btnDelivery;

    TextView srlogin;

    String selectedRole = "USER"; // default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        srname = findViewById(R.id.srname);
        srnumber = findViewById(R.id.srnumber);
        sremailid = findViewById(R.id.sremailid);
        srpassword = findViewById(R.id.srpassword);
        btnsubmit = findViewById(R.id.btnsubmit);
        srlogin = findViewById(R.id.srlogin);

        btnUser = findViewById(R.id.btnUser);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnDelivery = findViewById(R.id.btnDelivery);

        // 🔹 Default selection → USER
        highlightSelectedRole("USER");

        srlogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // ===== ROLE BUTTON CLICK (MERGED + COLOR CHANGE) =====
        btnUser.setOnClickListener(v -> {
            selectedRole = "USER";
            highlightSelectedRole("USER");
        });

        btnAdmin.setOnClickListener(v -> {
            selectedRole = "ADMIN";
            highlightSelectedRole("ADMIN");
        });

        btnDelivery.setOnClickListener(v -> {
            selectedRole = "DELIVERY";
            highlightSelectedRole("DELIVERY");
        });
        // ====================================================

        btnsubmit.setOnClickListener(v -> {

            if (srname.getText().toString().isEmpty()) {
                srname.setError("Please enter your name");
                return;
            }

            if (srnumber.getText().toString().isEmpty()) {
                srnumber.setError("Enter your number");
                return;
            }

            if (srnumber.getText().toString().length() != 10) {
                srnumber.setError("Enter valid mobile number");
                return;
            }

            if (sremailid.getText().toString().isEmpty()) {
                sremailid.setError("Enter your email");
                return;
            }

            if (!sremailid.getText().toString().endsWith("@gmail.com")) {
                sremailid.setError("Enter valid email id");
                return;
            }

            if (srpassword.getText().toString().isEmpty()) {
                srpassword.setError("Enter password");
                return;
            }

            if (srpassword.getText().toString().length() < 8) {
                srpassword.setError("Password must be at least 8 characters");
                return;
            }

            Toast.makeText(
                    SignupActivity.this,
                    "Registration Successful as " + selectedRole,
                    Toast.LENGTH_SHORT
            ).show();

            // Save data
            SharedPreferences prefs =
                    getSharedPreferences("UserProfile", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", srname.getText().toString());
            editor.putString("phone", srnumber.getText().toString());
            editor.putString("email", sremailid.getText().toString());
            editor.putString("role", selectedRole);
            editor.apply();

            // Role based navigation
            Intent intent;
            if (selectedRole.equals("ADMIN")) {
                intent = new Intent(this, AdminHomeActivity.class);
            } else if (selectedRole.equals("DELIVERY")) {
                intent = new Intent(this, D_Boy_MainActivity.class);
            } else {
                intent = new Intent(this, HomeActivity.class);
            }

            startActivity(intent);
            finish();
        });
    }

    // 🔥 ONLY NEW METHOD (UI HIGHLIGHT)
    private void highlightSelectedRole(String role) {

        // reset all
        btnUser.setBackgroundTintList(
                ContextCompat.getColorStateList(this, R.color.green));
        btnAdmin.setBackgroundTintList(
                ContextCompat.getColorStateList(this, R.color.green));
        btnDelivery.setBackgroundTintList(
                ContextCompat.getColorStateList(this, R.color.green));

        // highlight selected
        if (role.equals("USER")) {
            btnUser.setBackgroundTintList(
                    ContextCompat.getColorStateList(this, R.color.admin_status_delivered));
        } else if (role.equals("ADMIN")) {
            btnAdmin.setBackgroundTintList(
                    ContextCompat.getColorStateList(this, R.color.admin_status_delivered));
        } else {
            btnDelivery.setBackgroundTintList(
                    ContextCompat.getColorStateList(this, R.color.admin_status_delivered));
        }
    }
}
