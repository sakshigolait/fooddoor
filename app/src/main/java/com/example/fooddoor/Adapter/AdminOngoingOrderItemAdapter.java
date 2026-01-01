package com.example.fooddoor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Modelclass.AdminOngoingOrderItemModel;
import com.example.fooddoor.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdminOngoingOrderItemAdapter extends RecyclerView.Adapter<AdminOngoingOrderItemAdapter.AdminItemViewHolder> {

    private List<AdminOngoingOrderItemModel> adminItems;
    private DecimalFormat adminDf = new DecimalFormat("#0.00");

    public AdminOngoingOrderItemAdapter(List<AdminOngoingOrderItemModel> adminItems) {
        this.adminItems = adminItems;
    }

    @NonNull
    @Override
    public AdminItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_ongoing_order_item, parent, false);
        return new AdminItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminItemViewHolder holder, int position) {
        AdminOngoingOrderItemModel m = adminItems.get(position);
        holder.adminTextItemName.setText(m.adminItemName);
        holder.adminTextItemQty.setText("Qty: " + m.adminQty);
        holder.adminTextItemPrice.setText("$" + adminDf.format(m.adminPrice));
    }

    @Override
    public int getItemCount() {
        return adminItems.size();
    }

    static class AdminItemViewHolder extends RecyclerView.ViewHolder {
        TextView adminTextItemName, adminTextItemQty, adminTextItemPrice;

        public AdminItemViewHolder(@NonNull View itemView) {
            super(itemView);
            adminTextItemName = itemView.findViewById(R.id.adminOngoingTextItemName);
            adminTextItemQty = itemView.findViewById(R.id.adminOngoingTextItemQty);
            adminTextItemPrice = itemView.findViewById(R.id.adminOngoingTextItemPrice);
        }
    }
}