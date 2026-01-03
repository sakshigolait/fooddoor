package com.example.fooddoor.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.OrderModel;
import com.example.fooddoor.R;
import com.example.fooddoor.fragment.OrderDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.VH> {

    private List<OrderModel> list = new ArrayList<>();
    private final List<OrderModel> fullList = new ArrayList<>();

    public OrderAdapter(List<OrderModel> initial) {
        if (initial != null) {
            list.addAll(initial);
            fullList.addAll(initial);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        OrderModel m = list.get(pos);

        // 🔹 Item name (status ke upar)
        h.txtItemName.setText(m.getItemName());

        h.txtStatus.setText(m.getStatus());
        h.txtDate.setText(m.getDate());
        h.txtQty.setText("Qty: " + m.getQty());
        h.imgOrder.setImageResource(m.getImageRes());

        // 👉 Arrow click pe issi item ka detail open hoga
        h.imgNext.setOnClickListener(v -> {

            // 1️⃣ Data bundle me daalo (ab name bhi)
            Bundle bundle = new Bundle();
            bundle.putString("name", m.getItemName());   // ⭐ NEW
            bundle.putString("status", m.getStatus());
            bundle.putString("date", m.getDate());
            bundle.putInt("qty", m.getQty());
            bundle.putInt("image", m.getImageRes());

            // 2️⃣ Fragment banao & arguments set karo
            Fragment fragment = new OrderDetailFragment();
            fragment.setArguments(bundle);

            // 3️⃣ HomeActivity ke FrameLayout (homeframelayout) me replace karo
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeframelayout, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /** 🔍 Search filter: name + status + date */
    public void filter(String query) {
        list.clear();
        if (query == null || query.trim().isEmpty()) {
            list.addAll(fullList);
        } else {
            String q = query.toLowerCase();
            for (OrderModel m : fullList) {
                if (m.getItemName().toLowerCase().contains(q) ||      // item name
                        m.getStatus().toLowerCase().contains(q) ||    // status
                        m.getDate().toLowerCase().contains(q)) {      // date

                    list.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgOrder, imgNext;
        TextView txtItemName, txtStatus, txtDate, txtQty;

        VH(@NonNull View v) {
            super(v);
            imgOrder   = v.findViewById(R.id.imgOrder);
            imgNext    = v.findViewById(R.id.imgNext);
            txtItemName= v.findViewById(R.id.txtItemName);
            txtStatus  = v.findViewById(R.id.txtStatus);
            txtDate    = v.findViewById(R.id.txtDate);
            txtQty     = v.findViewById(R.id.txtQty);
        }
    }
}