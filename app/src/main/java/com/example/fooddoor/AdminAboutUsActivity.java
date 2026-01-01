package com.example.fooddoor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminAboutUsActivity extends AppCompatActivity {

    private TextInputEditText etAbout;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_about_us);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbaraboutus);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        etAbout = findViewById(R.id.et_about_us);
        btnSave = findViewById(R.id.btn_save_about);

        // Dummy existing text (later Firebase se load hoga)
        etAbout.setText(
                "We are a food delivery platform committed to serving fresh and delicious meals."
        );

        btnSave.setOnClickListener(v -> {
            String aboutText = etAbout.getText().toString().trim();

            if (aboutText.isEmpty()) {
                Toast.makeText(this, "About Us cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Save to Firebase / API
            Toast.makeText(this, "About Us updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
