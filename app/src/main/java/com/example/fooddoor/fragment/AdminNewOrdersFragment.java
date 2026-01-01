package com.example.fooddoor.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddoor.Adapter.AdminOrderAdapter;
import com.example.fooddoor.R;
import com.example.fooddoor.javaClass.Order;
import com.example.fooddoor.javaClass.OrderLine;

import java.util.ArrayList;
import java.util.List;

public class AdminNewOrdersFragment extends Fragment {

    RecyclerView rvAdminOrders;

    public AdminNewOrdersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_new_orders, container, false);

        rvAdminOrders = view.findViewById(R.id.rvAdminOrders);
        rvAdminOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Order> list = new ArrayList<>();

        // your image path
        String avatar = "/mnt/data/68dd062f-ecb3-4777-b48b-20f6ac2e1d61.png";

        List<OrderLine> lines1 = new ArrayList<>();
        lines1.add(new OrderLine("Dal Makhani", 2, 12));
        lines1.add(new OrderLine("Simple Thali", 1, 18));
        lines1.add(new OrderLine("Butter Nan", 2, 3));

        list.add(new Order(
                "Angel James",
                "Today at 12:23 AM",
                "348",
                "Please pack green sauce…",
                lines1,
                avatar
        ));

        list.add(new Order(
           "jone doe",
                "Today at 3:00 PM",
                "349",
                "",
                lines1,
                avatar
        ));

        rvAdminOrders.setAdapter(new AdminOrderAdapter(getContext(), list));

        return view;
    }
}
