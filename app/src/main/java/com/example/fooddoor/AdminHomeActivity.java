package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddoor.Adapter.AdminOrdersPageAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AdminHomeActivity extends AppCompatActivity {
    TabLayout adminTabLayout;
    ViewPager2 adminViewPager;
    AdminOrdersPageAdapter adminOrdersPageAdapter;
        private DrawerLayout drawerLayout;
        private NavigationView navigationView;
        private Toolbar toolbar;
        private ActionBarDrawerToggle toggle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_home);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            adminTabLayout = findViewById(R.id.adminTabLayout);
            adminViewPager = findViewById(R.id.adminViewPager);

            adminOrdersPageAdapter = new AdminOrdersPageAdapter(this);
            adminViewPager.setAdapter(adminOrdersPageAdapter);
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);

            // Drawer toggle (hamburger icon)
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            new TabLayoutMediator(adminTabLayout, adminViewPager, (tab, position) -> {

                if (position == 0) {
                    tab.setText("New Orders");

                } else if (position == 1) {
                    tab.setText("Ongoing Orders");
                } else if (position == 2) {
                    tab.setText("Past Orders");
                }

            }).attach();

            // Handle navigation item clicks
            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_dashboard) {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,AdminHomeActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_myprofile) {
                    Toast.makeText(this, "Manage Users Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,AdminProfileActivity.class);
                    startActivity(intent);
                }else if (id == R.id.nav_workers) {
                    Toast.makeText(this, "Manage Users Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,ManageWorkerActivity.class);
                    startActivity(intent);
                }else if (id == R.id.nav_category) {
                    Toast.makeText(this, "Manage Users Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,ManageCategoryActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_items) {
                    Toast.makeText(this, "manage item Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,ManageItemsActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_settings) {
                    Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,AdminSettingActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_logout) {
                    Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminHomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
        }
    @Override
        public void onBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }


}