package com.example.fooddoor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText srname, srnumber, sremailid, srpassword;
    Button btnsubmit;

    TextView srlogin;

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

        srlogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnsubmit.setOnClickListener(v -> {
            if (srname.getText().toString().isEmpty()) {
                srname.setError("Please enter your name");
            } else if (srnumber.getText().toString().isEmpty()) {
                srnumber.setError("Enter your number");
            } else if (srnumber.getText().toString().length() != 10) {
                srnumber.setError("Enter valid mobile number");
            } else if (sremailid.getText().toString().isEmpty()) {
                sremailid.setError("Enter your email");
            } else if (!sremailid.getText().toString().endsWith("@gmail.com")) {
                sremailid.setError("Enter valid email id");
            } else if (srpassword.getText().toString().isEmpty()) {
                srpassword.setError("Enter password");
            } else if (srpassword.getText().toString().length() < 8) {
                srpassword.setError("Password must be at least 8 characters");
            } else {
                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                // тнР SAVE Registration Data to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", srname.getText().toString());
                editor.putString("phone", srnumber.getText().toString());
                editor.putString("email", sremailid.getText().toString());
                editor.apply();  // Important: Save data

                // Open Home Screen
                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                intent.putExtra("name", srname.getText().toString());
                intent.putExtra("number", srnumber.getText().toString());
                intent.putExtra("email", sremailid.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}