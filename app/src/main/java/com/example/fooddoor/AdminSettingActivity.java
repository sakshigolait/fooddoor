package com.example.fooddoor;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class AdminSettingActivity extends AppCompatActivity {

    private SwitchMaterial switchAutoAccept, switchOrderSound,
            switchPush, switchEmailNotif;
    private TextView tvLanguageValue, tvVersion;
    private LinearLayout rowLanguage, rowAbout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_admin_setting);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Views
        switchAutoAccept = findViewById(R.id.adminSetting_switchAutoAccept);
        switchOrderSound = findViewById(R.id.adminSetting_switchOrderSound);
        switchPush = findViewById(R.id.adminSetting_switchPush);
        switchEmailNotif = findViewById(R.id.adminSetting_switchEmailNotif);

        tvLanguageValue = findViewById(R.id.adminSetting_textLanguageValue);
        tvVersion = findViewById(R.id.adminSetting_textVersion);
        rowLanguage = findViewById(R.id.adminSetting_rowLanguage);
        rowAbout = findViewById(R.id.adminSetting_rowAbout);

        // Default values (baad me SharedPreferences/Firebase se load kar sakte ho)
        loadDefaultSettings();

        // Listeners
        switchAutoAccept.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: save in SharedPreferences / backend
            Toast.makeText(this,
                    "Auto-accept new orders: " + (isChecked ? "ON" : "OFF"),
                    Toast.LENGTH_SHORT).show();
        });

        switchOrderSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this,
                    "Order sound: " + (isChecked ? "ON" : "OFF"),
                    Toast.LENGTH_SHORT).show();
        });

        switchPush.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this,
                    "Push notifications: " + (isChecked ? "ON" : "OFF"),
                    Toast.LENGTH_SHORT).show();
        });

        switchEmailNotif.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this,
                    "Email notifications: " + (isChecked ? "ON" : "OFF"),
                    Toast.LENGTH_SHORT).show();
        });

        rowLanguage.setOnClickListener(v -> {
            // TODO: Show language selection dialog
            Toast.makeText(this, "Language change popup yahan banega", Toast.LENGTH_SHORT).show();
        });

        rowAbout.setOnClickListener(v -> {
            // TODO: About screen / dialog
            Toast.makeText(this, "FoodDoor Admin App v1.0.0", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadDefaultSettings() {
        // Filhaal static; baad me SharedPreferences se load karna
        switchAutoAccept.setChecked(false);
        switchOrderSound.setChecked(true);
        switchPush.setChecked(true);
        switchEmailNotif.setChecked(false);

        tvLanguageValue.setText("English");
        tvVersion.setText("v1.0.0");
    }
}
