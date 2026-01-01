package com.example.fooddoor.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Modelclass.AdminPastOrderModel;
import com.example.fooddoor.R;
import com.example.fooddoor.AdminPastOrderDetailsActivity;
import java.text.DecimalFormat;
import java.util.List;


    public class AdminPastOrderAdapter extends RecyclerView.Adapter<AdminPastOrderAdapter.AdminOrderViewHolder> {

        private List<AdminPastOrderModel> adminOrderList;
        private DecimalFormat adminDf = new DecimalFormat("#0.00");

        public AdminPastOrderAdapter(List<AdminPastOrderModel> adminOrderList) {
            this.adminOrderList = adminOrderList;
        }

        @NonNull
        @Override
        public AdminOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_admin_past_order, parent, false);
            return new AdminOrderViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull AdminOrderViewHolder holder, int position) {
            AdminPastOrderModel order = adminOrderList.get(position);

            holder.adminTextCustomerName.setText(order.adminCustomerName);
            holder.adminTextTime.setText(order.adminTimeText);
            holder.adminTextOrderId.setText("Order id: " + order.adminOrderId);
            holder.adminTextTotal.setText("Total: $" + adminDf.format(order.getAdminTotal()));

            // Status text + color
            if (order.adminIsDelivered) {
                holder.adminTextStatus.setText("Order Status: Order Delivered");
                holder.adminTextStatus.setTextColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.admin_status_delivered));
            } else {
                holder.adminTextStatus.setText("Order Status: Order Cancelled");
                holder.adminTextStatus.setTextColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.admin_status_cancelled));
            }

            // inner RecyclerView
            holder.adminOrderItemsRecyclerView.setLayoutManager(
                    new LinearLayoutManager(holder.itemView.getContext()));
            holder.adminOrderItemsRecyclerView.setAdapter(
                    new AdminPastOrderItemAdapter(order.adminItems));

            // View details click
            holder.adminButtonViewDetails.setOnClickListener(v -> {
                Intent i = new Intent(v.getContext(), AdminPastOrderDetailsActivity.class);
                i.putExtra(AdminPastOrderDetailsActivity.EXTRA_ADMIN_PAST_ORDER, order);
                v.getContext().startActivity(i);
            });
            ;
        }

        @Override
        public int getItemCount() {
            return adminOrderList.size();
        }

        static class AdminOrderViewHolder extends RecyclerView.ViewHolder {

            ImageView adminImageAvatar;
            TextView adminTextCustomerName, adminTextTime, adminTextOrderId,
                    adminTextTotal, adminTextStatus;
            RecyclerView adminOrderItemsRecyclerView;
            Button adminButtonViewDetails;

            public AdminOrderViewHolder(@NonNull View itemView) {
                super(itemView);
                adminImageAvatar = itemView.findViewById(R.id.adminImageAvatar);
                adminTextCustomerName = itemView.findViewById(R.id.adminTextCustomerName);
                adminTextTime = itemView.findViewById(R.id.adminTextTime);
                adminTextOrderId = itemView.findViewById(R.id.adminTextOrderId);
                adminTextTotal = itemView.findViewById(R.id.adminTextTotal);
                adminTextStatus = itemView.findViewById(R.id.adminTextStatus);
                adminOrderItemsRecyclerView = itemView.findViewById(R.id.adminOrderItemsRecyclerView);
                adminButtonViewDetails = itemView.findViewById(R.id.adminButtonViewDetails);
            }
        }
    }

