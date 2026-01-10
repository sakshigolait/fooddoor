package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpVerificationActivity extends AppCompatActivity {

    EditText etOtp;
    Button btnVerifyOtp;

    int receivedOtp;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Bind views
        etOtp = findViewById(R.id.etOtp);
        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);

        // Get OTP & mobile from intent
        receivedOtp = getIntent().getIntExtra("otp", 0);
        mobile = getIntent().getStringExtra("mobile");

        btnVerifyOtp.setOnClickListener(v -> {

            String enteredOtpStr = etOtp.getText().toString().trim();

            if (enteredOtpStr.isEmpty()) {
                Toast.makeText(
                        OtpVerificationActivity.this,
                        "Please enter OTP",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int enteredOtp = Integer.parseInt(enteredOtpStr);

            // 🔹 OTP MATCH CHECK
            if (enteredOtp == receivedOtp) {

                Toast.makeText(
                        OtpVerificationActivity.this,
                        "OTP Verified Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                // 👉 NEXT SCREEN (Reset password / Login)
                Intent intent = new Intent(OtpVerificationActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(
                        OtpVerificationActivity.this,
                        "Invalid OTP",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
