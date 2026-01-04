package com.example.fooddoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class AdminEditCategoryActivity extends AppCompatActivity {

    private ShapeableImageView img;
    private TextInputEditText etName;
    private Uri newImageUri;
    private String categoryId;

    private final ActivityResultLauncher<String> imagePicker =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            newImageUri = uri;
                            img.setImageURI(uri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_category);

        Toolbar toolbar = findViewById(R.id.toolbar_edit_category);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        img = findViewById(R.id.editCategory_image);
        etName = findViewById(R.id.editCategory_etName);
        MaterialButton btnChangeImg = findViewById(R.id.btn_editCategory_changeImage);
        MaterialButton btnUpdate = findViewById(R.id.btn_editCategory_update);
        MaterialButton btnDelete = findViewById(R.id.btn_editCategory_delete);

        // Get category id
        categoryId = getIntent().getStringExtra("CATEGORY_ID");

        loadCategoryDetails(categoryId);

        btnChangeImg.setOnClickListener(v ->
                imagePicker.launch("image/*")
        );

        btnUpdate.setOnClickListener(v -> updateCategory());

        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    private void loadCategoryDetails(String id) {
        // TODO: Replace with Firebase fetch
        etName.setText("Snacks");
    }

    private void updateCategory() {
        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Category name required", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Upload image if changed + update category in DB
        Toast.makeText(this, "Category updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Category")
                .setMessage("Are you sure? This will remove all related items.")
                .setPositiveButton("Delete", (d, w) -> {
                    // TODO: delete from DB
                    Toast.makeText(this, "Category deleted", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
