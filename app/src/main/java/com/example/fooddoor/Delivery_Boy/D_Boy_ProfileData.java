package com.example.fooddoor.Delivery_Boy;

import android.os.Bundle;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fooddoor.R;

public class D_Boy_ProfileData extends AppCompatActivity {

    EditText edtName, edtPassword, edtPhone, edtEmail,etDialogInput;
    Button btnSave,btnCancel;
    ImageView btnBack, profileImage;
    TextView tvDialogTitle;

    private void showEditDialog(String title, EditText targetField) {
        AlertDialog.Builder builder = new AlertDialog.Builder(D_Boy_ProfileData.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.d_boy_dialog_edit_field, null);
        builder.setView(dialogView);

        tvDialogTitle = dialogView.findViewById(R.id.tvDialogTitle);
        etDialogInput = dialogView.findViewById(R.id.etDialogInput);
        btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnSave = dialogView.findViewById(R.id.btnSave);

        tvDialogTitle.setText("Change "+title);
        etDialogInput.setText(targetField.getText().toString());

        AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnSave.setOnClickListener(v -> {
            targetField.setText(etDialogInput.getText().toString());
            dialog.dismiss();
        });

        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dboy_profile_data);
        View main=findViewById(R.id.main);
        edtName = findViewById(R.id.edtName);
        edtName.setFocusable(false);
        edtName.setOnClickListener(v->
                showEditDialog("Full Name",edtName));
        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setFocusable(false);
        edtPassword.setOnClickListener(v->
                showEditDialog("Password",edtPassword));
        edtPhone = findViewById(R.id.edtPhone);
        edtPhone.setFocusable(false);
        edtPhone.setOnClickListener(v->
                showEditDialog("Phone Number",edtPhone));
        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setFocusable(false);
        edtEmail.setOnClickListener(v->
                showEditDialog("Email address",edtEmail));
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
        profileImage = findViewById(R.id.profileImage);
        btnSave.setOnClickListener(v ->
                Toast.makeText(this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show()
        );
        btnBack.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}