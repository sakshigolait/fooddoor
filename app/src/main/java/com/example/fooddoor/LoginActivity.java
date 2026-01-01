package com.example.fooddoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText lusername, lpassword;
    Button loginBtn;
    TextView forgetbtn, signupbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // View binding
        lusername = findViewById(R.id.lusername);
        lpassword = findViewById(R.id.lpassword);
        loginBtn = findViewById(R.id.loginbtn);
        forgetbtn = findViewById(R.id.forgetbtn);      // It's a TextView in XML
        signupbutton = findViewById(R.id.signupbutton);

        // Login button click handler
        loginBtn.setOnClickListener(v -> {
            String emailInput = lusername.getText().toString().trim();
            String passwordInput = lpassword.getText().toString().trim();

            if (validateInputs(emailInput, passwordInput)) {
                Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish(); // Optional: finish LoginActivity so user can't go back
            }
        });

        // Sign up click
        signupbutton.setOnClickListener(v -> {
           Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        // Forgot password click
        forgetbtn.setOnClickListener(v -> {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            // You can add navigation to ForgotPasswordActivity here if needed
        });
    }

    // Validates username and password
    private boolean validateInputs(String username, String password) {
        if (username.isEmpty()) {
            lusername.setError("Please Enter Username");
            return false;
        }
        if (username.length() <= 8) {
            lusername.setError("Username must be more than 8 characters");
            return false;
        }
        if (!username.matches(".*[A-Z].*")) {
            lusername.setError("Username must contain at least one uppercase letter");
            return false;
        }
        if (!username.matches(".*[a-z].*")) {
            lusername.setError("Username must contain at least one lowercase letter");
            return false;
        }
        if (!username.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            lusername.setError("Username must contain at least one special symbol");
            return false;
        }
        if (!username.matches(".*[0-9].*")) {
            lusername.setError("Username must contain at least one number");
            return false;
        }

        if (password.isEmpty()) {
            lpassword.setError("Please Enter Password");
            return false;
        }
        if (password.length() < 8) {
            lpassword.setError("Password must be at least 8 characters");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            lpassword.setError("Password must contain at least one uppercase letter");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            lpassword.setError("Password must contain at least one lowercase letter");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            lpassword.setError("Password must contain at least one special symbol");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            lpassword.setError("Password must contain at least one number");
            return false;
        }

        return true;
    }
}
