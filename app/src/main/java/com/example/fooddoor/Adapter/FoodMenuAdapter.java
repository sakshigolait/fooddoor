package com.example.fooddoor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.FoodItem;
import com.example.fooddoor.R;

import java.util.List;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.VH> {

    private List<FoodItem> items; // full + filtered list handle करने के लिए

    public FoodMenuAdapter(List<FoodItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        FoodItem f = items.get(pos);
        h.name.setText(f.getName());
        h.price.setText(f.getPrice());
        h.img.setImageResource(f.getImageRes());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ✅ यह method filter / category change के लिए
    public void updateList(List<FoodItem> filtered) {
        this.items = filtered;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;

        VH(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgFood);
            name = v.findViewById(R.id.txtName);
            price = v.findViewById(R.id.txtPrice);
        }
    }
}
