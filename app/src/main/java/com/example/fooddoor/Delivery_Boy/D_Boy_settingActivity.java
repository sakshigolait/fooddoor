package com.example.fooddoor.Delivery_Boy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fooddoor.R;

public class D_Boy_settingActivity extends AppCompatActivity {

    Switch switchNotifications, switchLocation;
    TextView btnClearCache, btnTerms, btnPrivacy;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dboy_setting);

        btnBack = findViewById(R.id.btnBack);
        switchNotifications = findViewById(R.id.switchNotifications);
        switchLocation = findViewById(R.id.switchLocation);
        btnClearCache = findViewById(R.id.btnClearCache);
        btnTerms = findViewById(R.id.btnTerms);
        btnPrivacy = findViewById(R.id.btnPrivacy);

        btnBack.setOnClickListener(v -> finish());

        btnClearCache.setOnClickListener(v ->
                Toast.makeText(this, "Cache Cleared!", Toast.LENGTH_SHORT).show()
        );

        btnTerms.setOnClickListener(v ->
                Toast.makeText(this, "Terms clicked", Toast.LENGTH_SHORT).show()
        );

        btnPrivacy.setOnClickListener(v ->
                Toast.makeText(this, "Privacy policy clicked", Toast.LENGTH_SHORT).show()
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Settingmain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
