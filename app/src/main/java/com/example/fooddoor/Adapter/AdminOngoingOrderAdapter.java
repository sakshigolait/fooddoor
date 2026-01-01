package com.example.fooddoor.Adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.AdminOngoingOrderDetailsActivity;
import com.example.fooddoor.Modelclass.AdminOngoingOrderModel;
import com.example.fooddoor.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdminOngoingOrderAdapter extends RecyclerView.Adapter<AdminOngoingOrderAdapter.AdminOngoingViewHolder> {

    private List<AdminOngoingOrderModel> adminOrderList;
    private DecimalFormat adminDf = new DecimalFormat("#0.00");

    private String[] statusOptions = new String[]{
            "Dispatched",
            "Preparing",
            "On the way"
    };

    public AdminOngoingOrderAdapter(List<AdminOngoingOrderModel> adminOrderList) {
        this.adminOrderList = adminOrderList;
    }

    @NonNull
    @Override
    public AdminOngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_ongoing_order, parent, false);
        return new AdminOngoingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOngoingViewHolder holder, int position) {
        AdminOngoingOrderModel order = adminOrderList.get(position);

        holder.adminTextCustomerName.setText(order.adminCustomerName);
        holder.adminTextTime.setText(order.adminTimeText);
        holder.adminTextOrderId.setText("Order id: " + order.adminOrderId);
        holder.adminTextTotal.setText("Total: $" + adminDf.format(order.getAdminTotal()));

        // Inner RecyclerView
        holder.adminOrderItemsRecyclerView.setLayoutManager(
                new LinearLayoutManager(holder.itemView.getContext()));
        holder.adminOrderItemsRecyclerView.setAdapter(
                new AdminOngoingOrderItemAdapter(order.adminItems));

        // Status spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                holder.itemView.getContext(),
                android.R.layout.simple_spinner_item,
                statusOptions
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.adminSpinnerStatus.setAdapter(spinnerAdapter);

        int index = 0;
        if (order.adminCurrentStatus != null) {
            for (int i = 0; i < statusOptions.length; i++) {
                if (statusOptions[i].equals(order.adminCurrentStatus)) {
                    index = i;
                    break;
                }
            }
        }
        holder.adminSpinnerStatus.setSelection(index, false);

        holder.adminSpinnerStatus.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(android.widget.AdapterView<?> parent, View view, int pos, long id) {
                        order.adminCurrentStatus = statusOptions[pos];
                        // future: yahi se Firebase update kar sakte ho
                    }

                    @Override
                    public void onNothingSelected(android.widget.AdapterView<?> parent) {}
                });

        // ⭐ VIEW DETAILS — yahin click se activity open hogi
        holder.adminButtonViewDetails.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), AdminOngoingOrderDetailsActivity.class);
            i.putExtra(AdminOngoingOrderDetailsActivity.EXTRA_ADMIN_ONGOING_ORDER, order);
            v.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return adminOrderList.size();
    }

    static class AdminOngoingViewHolder extends RecyclerView.ViewHolder {

        ImageView adminImageAvatar;
        TextView adminTextCustomerName, adminTextTime, adminTextOrderId, adminTextTotal;
        RecyclerView adminOrderItemsRecyclerView;
        Spinner adminSpinnerStatus;
        Button adminButtonViewDetails;

        public AdminOngoingViewHolder(@NonNull View itemView) {
            super(itemView);
            adminImageAvatar = itemView.findViewById(R.id.adminOngoingImageAvatar);
            adminTextCustomerName = itemView.findViewById(R.id.adminOngoingTextCustomerName);
            adminTextTime = itemView.findViewById(R.id.adminOngoingTextTime);
            adminTextOrderId = itemView.findViewById(R.id.adminOngoingTextOrderId);
            adminTextTotal = itemView.findViewById(R.id.adminOngoingTextTotal);
            adminOrderItemsRecyclerView = itemView.findViewById(R.id.adminOngoingOrderItemsRecyclerView);
            adminSpinnerStatus = itemView.findViewById(R.id.adminOngoingSpinnerStatus);
            adminButtonViewDetails = itemView.findViewById(R.id.adminOngoingButtonViewDetails);
        }
    }
}