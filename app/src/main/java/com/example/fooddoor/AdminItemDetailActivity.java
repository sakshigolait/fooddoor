package com.example.fooddoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fooddoor.Modelclass.AdminMenuItem;

public class AdminItemDetailActivity extends AppCompatActivity {

    private ImageView itemImage;
    private EditText etName, etPrice, etShortDesc;
    private Button btnSaveUpdate, btnDelete;

    private String itemId;
    private Uri selectedImageUri;
    private AdminMenuItem currentItem;

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    itemImage.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_detail);

        itemImage = findViewById(R.id.admin_detail_image);
        etName = findViewById(R.id.et_admin_item_name);
        etPrice = findViewById(R.id.et_admin_item_price);
        etShortDesc = findViewById(R.id.et_admin_short_desc);
        btnSaveUpdate = findViewById(R.id.btn_save_update);
        btnDelete = findViewById(R.id.btn_delete_item);
        Button btnSelectImage = findViewById(R.id.btn_select_image);

        itemId = getIntent().getStringExtra("ITEM_ID");

        if (itemId != null) {
            loadExistingItemDetails(itemId);
            btnSaveUpdate.setText("Update Item");
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnSaveUpdate.setText("Add New Item");
            btnDelete.setVisibility(View.GONE);
        }

        btnSelectImage.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        btnSaveUpdate.setOnClickListener(v -> handleSaveOrUpdate());
        btnDelete.setOnClickListener(v -> handleDelete());
    }

    private void loadExistingItemDetails(String id) {
        currentItem = new AdminMenuItem(
                id,
                "Existing Item",
                "Dessert",
                5.00,
                "https://example.com/dessert.jpg",
                "A sweet treat."
        );

        etName.setText(currentItem.getName());
        etPrice.setText(String.valueOf(currentItem.getPrice()));
        etShortDesc.setText(currentItem.getShortDescription());

        Glide.with(this)
                .load(currentItem.getImageUrl())
                .placeholder(R.drawable.blueberrycake)
                .into(itemImage);
    }

    private void handleSaveOrUpdate() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String desc = etShortDesc.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Name and Price required", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        String imageUrl = currentItem != null ? currentItem.getImageUrl() : null;

        Toast.makeText(this, "Item Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    // ✅ IMPORTANT PART
    private void handleDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete", (dialog, which) -> {

                    // TODO: Backend delete API here

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("DELETED_ITEM_ID", currentItem.getId());
                    setResult(RESULT_OK, resultIntent);

                    Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
