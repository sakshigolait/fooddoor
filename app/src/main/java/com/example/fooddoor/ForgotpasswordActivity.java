package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotpasswordActivity extends AppCompatActivity {

    EditText etMobile;
    Button btnSendOtp;
    TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        // 🔹 Bind views
        etMobile = findViewById(R.id.etMobile);
        btnSendOtp = findViewById(R.id.btnSendOtp);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        // 🔹 Send OTP button click
        btnSendOtp.setOnClickListener(v -> {

            String mobile = etMobile.getText().toString().trim();

            if (mobile.isEmpty()) {
                Toast.makeText(
                        ForgotpasswordActivity.this,
                        "Please enter mobile number",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (mobile.length() != 10) {
                Toast.makeText(
                        ForgotpasswordActivity.this,
                        "Enter valid 10-digit mobile number",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // 🔹 SIMULATED OTP (demo purpose – FREE)
            int otp = (int) (Math.random() * 900000) + 100000;

            Toast.makeText(
                    ForgotpasswordActivity.this,
                    "OTP sent successfully: " + otp,
                    Toast.LENGTH_LONG
            ).show();

            // 🔹 Navigate to OTP Verification screen
            Intent intent = new Intent(
                    ForgotpasswordActivity.this,
                    OtpVerificationActivity.class
            );
            intent.putExtra("otp", otp);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        });

        // 🔹 Back to Login
        tvBackToLogin.setOnClickListener(v -> {
            finish(); // return to LoginActivity
        });
    }
}
