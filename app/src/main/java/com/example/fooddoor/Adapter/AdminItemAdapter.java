package com.example.fooddoor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddoor.AdminItemDetailActivity;
import com.example.fooddoor.Modelclass.AdminMenuItem;
import com.example.fooddoor.R;

import java.util.List;

public class AdminItemAdapter extends RecyclerView.Adapter<AdminItemAdapter.AdminItemViewHolder> {

    private final Context context;
    private final List<AdminMenuItem> itemList;
    private final ActivityResultLauncher<Intent> itemDetailLauncher;

    // ✅ UPDATED CONSTRUCTOR
    public AdminItemAdapter(Context context,
                            List<AdminMenuItem> itemList,
                            ActivityResultLauncher<Intent> itemDetailLauncher) {
        this.context = context;
        this.itemList = itemList;
        this.itemDetailLauncher = itemDetailLauncher;
    }

    @NonNull
    @Override
    public AdminItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_admin_food_card, parent, false);
        return new AdminItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminItemViewHolder holder, int position) {
        AdminMenuItem item = itemList.get(position);

        holder.itemName.setText(item.getName());
        holder.itemCategory.setText("Category: " + item.getCategory());
        holder.itemPrice.setText("₹" + String.format("%.2f", item.getPrice()));

        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.blueberrycake)
                .into(holder.itemImage);

        // ✅ OPEN DETAIL SCREEN USING LAUNCHER
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AdminItemDetailActivity.class);
            intent.putExtra("ITEM_ID", item.getId());
            itemDetailLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder
    public static class AdminItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemCategory, itemPrice;

        public AdminItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.admin_item_image);
            itemName = itemView.findViewById(R.id.admin_item_name);
            itemCategory = itemView.findViewById(R.id.admin_item_category);
            itemPrice = itemView.findViewById(R.id.admin_item_price);
        }
    }

    // ✅ CALLED FROM ACTIVITY WHEN DELETE RESULT RECEIVED
    public void removeItemById(String itemId) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(itemId)) {
                itemList.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, itemList.size());
                break;
            }
        }
    }
}
