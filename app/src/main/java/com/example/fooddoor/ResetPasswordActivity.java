package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etNewPassword, etConfirmPassword;
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(v -> {

            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (newPass.length() < 6) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        "Password must be at least 6 characters",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // ✅ SUCCESS (simulated)
            Toast.makeText(
                    ResetPasswordActivity.this,
                    "Password reset successfully",
                    Toast.LENGTH_SHORT
            ).show();

            // Go back to Login
            Intent intent = new Intent(
                    ResetPasswordActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
            finish();
        });
    }
}
