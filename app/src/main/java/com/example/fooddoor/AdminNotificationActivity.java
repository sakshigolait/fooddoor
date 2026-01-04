package com.example.fooddoor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.AdminNotificationAdapter;
import com.example.fooddoor.Modelclass.AdminNotificationModel;

import java.util.ArrayList;
import java.util.List;

public class AdminNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);

        Toolbar toolbar = findViewById(R.id.toolbarNotification);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.rvNotifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AdminNotificationModel> list = new ArrayList<>();
        list.add(new AdminNotificationModel(
                "Order Delivered",
                "Your order #1234 has been delivered",
                "2h"));

        list.add(new AdminNotificationModel(
                "New Offer",
                "Flat 20% off on your next order",
                "5h"));

        recyclerView.setAdapter(new AdminNotificationAdapter(list));
    }
}
