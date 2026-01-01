package com.example.fooddoor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddoor.Adapter.AdminOngoingOrderAdapter;
import com.example.fooddoor.Modelclass.AdminOngoingOrderItemModel;
import com.example.fooddoor.Modelclass.AdminOngoingOrderModel;
import com.example.fooddoor.R;

import java.util.ArrayList;
import java.util.List;

public class AdminOngoingOrdersFragment extends Fragment {

    RecyclerView adminOngoingOrdersRecyclerView;

    public AdminOngoingOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_ongoing_orders, container, false);

        adminOngoingOrdersRecyclerView = view.findViewById(R.id.adminOngoingOrdersRecyclerView);
        adminOngoingOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<AdminOngoingOrderModel> adminOrderList = new ArrayList<>();

        List<AdminOngoingOrderItemModel> items1 = new ArrayList<>();
        items1.add(new AdminOngoingOrderItemModel("Dal Makhani", 2, 24));
        items1.add(new AdminOngoingOrderItemModel("Simple Thali - Veg", 1, 18));
        items1.add(new AdminOngoingOrderItemModel("Deluxe Thali - Non Veg", 2, 48));
        items1.add(new AdminOngoingOrderItemModel("Missi Roti", 5, 10));
        items1.add(new AdminOngoingOrderItemModel("Butter Nan", 2, 6));

        adminOrderList.add(new AdminOngoingOrderModel(
                "Angel James",
                "Today at 12:33 AM",
                "348",
                items1,
                "Order Dispatched"
        ));

        adminOrderList.add(new AdminOngoingOrderModel(
                "John Doe",
                "Today at 12:33 AM",
                "349",
                new ArrayList<>(items1),
                "Order Preparing"
        ));

        adminOrderList.add(new AdminOngoingOrderModel(
                "Angel James",
                "Today at 01:10 AM",
                "350",
                new ArrayList<>(items1),
                "On the way"
        ));

        AdminOngoingOrderAdapter adapter = new AdminOngoingOrderAdapter(adminOrderList);
        adminOngoingOrdersRecyclerView.setAdapter(adapter);

        return view;
    }
}