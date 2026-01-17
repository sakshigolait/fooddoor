package com.example.fooddoor;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_Boy_AvailableDeliveries extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DeliveryModel> list;
    DeliveryAdapter adapter;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dboy_available_deliveries);

        recyclerView = findViewById(R.id.recyclerDeliveries);
        iv_back = findViewById(R.id.iv_back);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        iv_back.setOnClickListener(v -> finish());

        list = new ArrayList<>();

        // 🔴 DELIVERY 1
        list.add(new DeliveryModel(
                "ORD101",
                "Online",
                "₹110",
                "Rajapeth",
                20.9301, 77.7495,
                "Sarafa, Amravati",
                20.932142, 77.746864,
                "2 min ago"
        ));

        list.add(new DeliveryModel(
                "ORD102",
                "Cash on delivery",
                "₹95",
                "Badnera Road",
                20.9412, 77.7601,
                "Shegaon–Rahatgaon Road, Amravati",
                20.955519, 77.764240,
                "7 min ago"
        ));

        list.add(new DeliveryModel(
                "ORD103",
                "Online",
                "₹150",
                "Camp Area",
                20.9289, 77.7478,
                "Kathora Naka, Amravati",
                20.930700, 77.758700,
                "12 min ago"
        ));
        adapter = new DeliveryAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}