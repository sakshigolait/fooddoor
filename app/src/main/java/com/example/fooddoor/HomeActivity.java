package com.example.fooddoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddoor.fragment.CartFragment;
import com.example.fooddoor.fragment.HomeFragment;
import com.example.fooddoor.fragment.OrderFragment;
import com.example.fooddoor.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public boolean doubletap = false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    OrderFragment orderFragment = new OrderFragment();
    CartFragment chatFragment = new CartFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Safe check to avoid crash if there's no ActionBar
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

        bottomNavigationView = findViewById(R.id.homeBottomNevigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.hhome);
    }

    public void Welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("FOODDOOR");
        ad.setMessage("Welcome to FOODDOOR");
        ad.setPositiveButton("Thank You", (dialog, which) -> dialog.cancel());
        ad.create().show();

        // Fixed the typo in key name
        editor.putBoolean("isFirstTime", false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menusetting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SettingActivity.class));

        } else if (item.getItemId() == R.id.menucontactus) {
            Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ContactusActivity.class));

        }
        else if (item.getItemId() == R.id.notification) {
            Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, NotificationActivity.class));

        } else if (item.getItemId() == R.id.favourite) {
            Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, FavouriteActivity.class));

        } else if (item.getItemId() == R.id.menulogout) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Logout");
            ad.setMessage("Are you sure you want to logout?");
            ad.setPositiveButton("Cancel", (dialog, which) -> dialog.cancel());
            ad.setNegativeButton("Logout", (dialog, which) -> {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            });
            ad.create().show();
        }

        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (doubletap) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            doubletap = true;
            new Handler().postDelayed(() -> doubletap = false, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.hhome) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout, homeFragment).commit();
        } else if (item.getItemId() == R.id.horder) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout, orderFragment).commit();
        } else if (item.getItemId() == R.id.hcart) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout, chatFragment).commit();
        } else if (item.getItemId() == R.id.hprofile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout, profileFragment).commit();
        }
        return true;
    }
}
