package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fooddoor.LoginActivity;
import com.example.fooddoor.R;

public class SignupActivity extends AppCompatActivity {

    EditText srname,srnumber,sremailid,registerusername,srpassword;
    Button btnsubmit;
    TextView tvregisterend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        srname = findViewById(R.id.srname);
        srnumber = findViewById(R.id.srnumber);
        sremailid = findViewById(R.id.sremailid);
        srpassword = findViewById(R.id.srpassword);
        btnsubmit = findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (srname.getText().toString().isEmpty())
                {
                    srname.setError("please Enter Your Name");
                } else if (srnumber.getText().toString().isEmpty()) {
                    srnumber.setError("Enter your number");
                } else if (srnumber.getText().toString().length()!=10) {
                    srnumber.setError("Enter valid Mobile Number");
                } else if (sremailid.getText().toString().isEmpty()) {
                    sremailid.setError("Enter your email");
                }else if (!sremailid.getText().toString().endsWith("@gmail.com")) {
                    sremailid.setError("Enter valid Email id");
                } else if (srpassword.getText().toString().isEmpty()) {
                    srpassword.setError("Enter password");
                } else if (srpassword.getText().toString().length()<=8) {
                    srpassword.setError("password must contain 8 char");
                } else if (srpassword.getText().toString().matches(".*[!@#$%^&*].*")) {
                    srpassword.setError("password must contain one symbol");
                }else {
                    Toast.makeText(SignupActivity.this,"Registation Successfully Done",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}