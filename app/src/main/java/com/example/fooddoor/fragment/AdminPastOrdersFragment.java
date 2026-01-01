package com.example.fooddoor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddoor.Adapter.AdminPastOrderAdapter;
import com.example.fooddoor.Modelclass.AdminOrderItemModel;
import com.example.fooddoor.Modelclass.AdminPastOrderModel;
import com.example.fooddoor.R;

import java.util.ArrayList;
import java.util.List;

public class AdminPastOrdersFragment extends Fragment {


    RecyclerView adminPastOrdersRecyclerView;

    public AdminPastOrdersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_past_orders, container, false);

        adminPastOrdersRecyclerView = view.findViewById(R.id.adminPastOrdersRecyclerView);
        adminPastOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<AdminPastOrderModel> adminOrderList = new ArrayList<>();

        List<AdminOrderItemModel> items1 = new ArrayList<>();
        items1.add(new AdminOrderItemModel("Dal Makhani", 2, 24));
        items1.add(new AdminOrderItemModel("Simple Thali - Veg", 1, 18));
        items1.add(new AdminOrderItemModel("Deluxe Thali - Non Veg", 2, 48));
        items1.add(new AdminOrderItemModel("Missi Roti", 5, 10));
        items1.add(new AdminOrderItemModel("Butter Nan", 2, 6));

        adminOrderList.add(new AdminPastOrderModel(
                "Angel James",
                "Today at 12:33 AM",
                "348",
                items1,
                true   // delivered
        ));

        adminOrderList.add(new AdminPastOrderModel(
                "John Doe",
                "Today at 12:33 AM",
                "349",
                new ArrayList<>(items1),
                false  // cancelled
        ));

        adminOrderList.add(new AdminPastOrderModel(
                "Angel James",
                "Today at 01:10 AM",
                "350",
                new ArrayList<>(items1),
                true
        ));

        AdminPastOrderAdapter adapter = new AdminPastOrderAdapter(adminOrderList);
        adminPastOrdersRecyclerView.setAdapter(adapter);

        return view;
    }
}