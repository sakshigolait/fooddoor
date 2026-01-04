package com.example.fooddoor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.fooddoor.D_Boy_HistoryFragment;
import com.example.fooddoor.D_Boy_HomeFragment;
import com.example.fooddoor.D_Boy_ProfileFragment;
import com.example.fooddoor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class D_Boy_MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ SAME layout jisme BottomNavigationView + FrameLayout hai
        setContentView(R.layout.activity_dboy_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 🔴 Safety check (crash se bachata hai)
        if (bottomNavigationView == null) {
            throw new RuntimeException("BottomNavigationView is null. Check ID & layout.");
        }

        // ✅ Default fragment (Home)
        loadFragment(new D_Boy_HomeFragment());

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
                loadFragment(selectedFragment);
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}