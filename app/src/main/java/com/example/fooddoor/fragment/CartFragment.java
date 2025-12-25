package com.example.fooddoor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.CartAdapter;
import com.example.fooddoor.CartModel;
import com.example.fooddoor.SharedCartData; // ✅ yaha CartData ki jagah singleton
import com.example.fooddoor.R;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private CartAdapter adapter;
    private List<CartModel> list;
    private TextView tvTotalAmount;
    private Button btnPlaceOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecycler = v.findViewById(R.id.cartRecycler);
        tvTotalAmount = v.findViewById(R.id.tvTotalAmount);
        btnPlaceOrder = v.findViewById(R.id.btnPlaceOrder);

        cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // ✅ Get list from SharedCartData singleton
        list = SharedCartData.getInstance().getCartItems();

        adapter = new CartAdapter(getContext(), list, () -> updateTotal());
        cartRecycler.setAdapter(adapter);

        updateTotal();

        btnPlaceOrder.setOnClickListener(view -> {
            int total = calculateTotal();
            Toast.makeText(getContext(), "Place order: ₹" + total, Toast.LENGTH_SHORT).show();
        });

        return v;
    }

    private void updateTotal() {
        int total = calculateTotal();
        tvTotalAmount.setText("₹" + total);
    }

    private int calculateTotal() {
        int sum = 0;
        for (CartModel c : list) {
            sum += c.getPrice() * c.getQuantity();
        }
        return sum;
    }
}