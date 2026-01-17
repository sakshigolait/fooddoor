package com.example.fooddoor.Delivery_Boy;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fooddoor.Delivery_Boy.D_Boy_HistoryFragment;
import com.example.fooddoor.Delivery_Boy.D_Boy_ProfileFragment;
import com.example.fooddoor.R;
import com.example.fooddoor.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//test commit
public class D_Boy_MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public boolean doubletap = false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    D_Boy_HomeFragment HomeFragment = new D_Boy_HomeFragment();
    D_Boy_HistoryFragment HistoryFragment = new D_Boy_HistoryFragment();
    D_Boy_ProfileFragment ProfileFragment = new D_Boy_ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dboy_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(getResources().getColor(R.color.white))
            );
            getSupportActionBar().setTitle("FOODDOOR");
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
        if (isFirstTime) {
            Welcome();
        }

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

    private void Welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("FOODDOOR");
        ad.setMessage("Welcome to FOODDOOR");
        ad.setPositiveButton("Thank You", (dialog, which) -> dialog.cancel());
        ad.create().show();

        editor.putBoolean("isFirstTime", false).commit();
    }
}





