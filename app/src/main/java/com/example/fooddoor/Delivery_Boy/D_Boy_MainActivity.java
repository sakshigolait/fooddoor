package com.example.fooddoor.Delivery_Boy;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fooddoor.Delivery_Boy.D_Boy_HistoryFragment;
import com.example.fooddoor.Delivery_Boy.D_Boy_ProfileFragment;
import com.example.fooddoor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class D_Boy_MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load default fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new D_Boy_HomeFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new D_Boy_HomeFragment();
            } else if (id == R.id.nav_history) {
                selectedFragment = new D_Boy_HistoryFragment();
            } else if (id == R.id.nav_profile) {
                selectedFragment = new D_Boy_ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}





