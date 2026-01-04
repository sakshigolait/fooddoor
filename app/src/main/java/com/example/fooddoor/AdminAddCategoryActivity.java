package com.example.fooddoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class AdminAddCategoryActivity extends AppCompatActivity {

    private ShapeableImageView imgCategory;
    private TextInputEditText etCategoryName;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> imagePicker =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            selectedImageUri = uri;
                            imgCategory.setImageURI(uri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_add_category);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Views
        imgCategory = findViewById(R.id.adminCategory_image);
        etCategoryName = findViewById(R.id.adminCategory_etName);
        MaterialButton btnSelectImage = findViewById(R.id.btn_adminCategory_selectImage);
        MaterialButton btnAdd = findViewById(R.id.btn_adminCategory_add);

        // Image picker
        btnSelectImage.setOnClickListener(v ->
                imagePicker.launch("image/*")
        );

        // Add category
        btnAdd.setOnClickListener(v -> {
            String name = etCategoryName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter category name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedImageUri == null) {
                Toast.makeText(this, "Select category image", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Upload image + save category to Firebase
            Toast.makeText(this, "Category Added: " + name, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
