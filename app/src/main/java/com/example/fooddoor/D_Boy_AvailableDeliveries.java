package com.example.fooddoor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_Boy_AvailableDeliveries extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DeliveryModel> list;
    DeliveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dboy_available_deliveries);

        recyclerView = findViewById(R.id.recyclerDeliveries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        list.add(new DeliveryModel(
                "ACR147856",
                "Online",
                "$32.00",
                "48, Hunters Road, Vepery",
                "Great Western, McC Lane, Fort",
                "1 min ago"));

        list.add(new DeliveryModel(
                "AWQ145698",
                "Cash on delivery",
                "$35.00",
                "491, Sai Section, Ambernath",
                "21, 5th Cross Double Road",
                "20 min ago"));

        list.add(new DeliveryModel(
                "TRE123654",
                "Online",
                "$40.00",
                "175 Jawahar Ngr, Amizara",
                "Jaina Tower Distt Centre",
                "45 min ago"));

        adapter = new DeliveryAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}