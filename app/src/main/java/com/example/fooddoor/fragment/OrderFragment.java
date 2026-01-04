package com.example.fooddoor.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fooddoor.Adapter.OrderAdapter;
import com.example.fooddoor.OrderModel;
import com.example.fooddoor.R;
import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order, container, false);

        RecyclerView rv = v.findViewById(R.id.rvOrders);
        EditText etSearch = v.findViewById(R.id.etSearchOrder);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderAdapter(getDummyOrders());
        rv.setAdapter(adapter);

        // 🔥 Search bar Live filtering
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // 💡 As you type → auto filter
            }

            @Override public void afterTextChanged(Editable s) {}
        });

        return v;
    }

    private List<OrderModel> getDummyOrders() {
        List<OrderModel> list = new ArrayList<>();
        list.add(new OrderModel("Momos", "On the way", "28 July 25", 1, R.drawable.momos));
        list.add(new OrderModel("Pasta", "On the way", "28 July 25", 2, R.drawable.pasta));
        list.add(new OrderModel("Sandwich", "Cancelled", "28 July 25", 1, R.drawable.sandwich2));
        list.add(new OrderModel("Burger", "Delivered", "27 July 25", 3, R.drawable.burger));
        list.add(new OrderModel("Spaghetti", "Cancelled", "26 July 25", 1, R.drawable.spegettiii));
        list.add(new OrderModel("Cherry Ice Cream", "Delivered", "26 July 25", 1, R.drawable.redcherryicecream));
        list.add(new OrderModel("Red Velvet Cake", "Delivered", "25 July 25", 1, R.drawable.redvelvetcake));
        list.add(new OrderModel("Cappuccino", "Cancelled", "25 July 25", 1, R.drawable.capachino));
        list.add(new OrderModel("Pineapple Strawberry Mix", "Delivered", "26 July 25", 1, R.drawable.pinapplestrawberrymix));
        list.add(new OrderModel("Margherita Pizza", "Delivered", "26 July 25", 1, R.drawable.margritapizaa));
        return list;
    }
}