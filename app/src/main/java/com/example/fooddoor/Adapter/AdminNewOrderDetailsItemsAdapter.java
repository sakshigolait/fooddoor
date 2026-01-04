package com.example.fooddoor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Modelclass.AdminNewOrderItemDetailsModel;
import com.example.fooddoor.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdminNewOrderDetailsItemsAdapter extends RecyclerView.Adapter<AdminNewOrderDetailsItemsAdapter.DetailsViewHolder> {

    private List<AdminNewOrderItemDetailsModel> detailsItems;
    private DecimalFormat df = new DecimalFormat("#0.00");

    public AdminNewOrderDetailsItemsAdapter(List<AdminNewOrderItemDetailsModel> detailsItems) {
        this.detailsItems = detailsItems;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_new_order_detail_item, parent, false);
        return new DetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        AdminNewOrderItemDetailsModel item = detailsItems.get(position);
        holder.item.setText(item.newOrder_detailsItemName);
        holder.qty.setText("Qty: " + item.newOrder_detailsQty);
        holder.price.setText("$" + df.format(item.newOrder_detailsPrice));
    }

    @Override
    public int getItemCount() {
        return detailsItems.size();
    }

    static class DetailsViewHolder extends RecyclerView.ViewHolder {

        TextView item, qty, price;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.adminNewDetail_textItemName);
            qty = itemView.findViewById(R.id.adminNewDetail_textItemQty);
            price = itemView.findViewById(R.id.adminNewDetail_textItemPrice);
        }
    }
}