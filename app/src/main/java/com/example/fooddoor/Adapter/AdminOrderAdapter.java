package com.example.fooddoor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddoor.AdminNewOrderDetailsActivity;
import com.example.fooddoor.R;
import com.example.fooddoor.javaClass.Order;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.VH> {

    private List<Order> orders;
    private Context ctx;
    private DecimalFormat df = new DecimalFormat("#.00");

    public AdminOrderAdapter(Context ctx, List<Order> orders) {
        this.ctx = ctx;
        this.orders = orders;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_admin_order_card, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Order o = orders.get(pos);

        h.tvCustomer.setText(o.customerName);
        h.tvTime.setText(o.time);
        h.tvOrderId.setText("Order ID: " + o.orderId);
        h.tvMessage.setText("Message: " + o.message);
        h.tvTotal.setText("Total: $" + df.format(o.getTotal()));

        // Avatar
        Glide.with(ctx)
                .load(Uri.fromFile(new File(o.avatarPath)))
                .into(h.imgAvatar);

        // Nested item list
        h.rvLines.setLayoutManager(new LinearLayoutManager(ctx));
        h.rvLines.setAdapter(new AdminOrderLineAdapter(o.lines));

        // CANCEL BUTTON
        h.btnCancel.setOnClickListener(v -> {
            int index = h.getAdapterPosition();
            if (index != RecyclerView.NO_POSITION) {
                orders.remove(index);
                notifyItemRemoved(index);
            }
        });

        // ACCEPT BUTTON → dialog open
        h.btnAccept.setOnClickListener(v -> {
            showDeliveryBoyDialog(o);
        });

        // CALL BUTTON
        h.btnCall.setOnClickListener(v -> {
            if (o.phone != null && !o.phone.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + o.phone));
                ctx.startActivity(intent);
            } else {
                Toast.makeText(ctx, "Phone number not available", Toast.LENGTH_SHORT).show();
            }
        });

        // VIEW DETAILS BUTTON (FIXED)
        h.btnView.setOnClickListener(v -> {
            Intent i = new Intent(ctx, AdminNewOrderDetailsActivity.class);
            i.putExtra(AdminNewOrderDetailsActivity.EXTRA_ADMIN_NEW_ORDER_DETAILS, o);
            ctx.startActivity(i);
        });
    }

    // 🔥 DELIVERY BOY DIALOG METHOD (COMPLETE)
    private void showDeliveryBoyDialog(Order order) {
        View dialogView = LayoutInflater.from(ctx)
                .inflate(R.layout.dialog_admin_new_detail_delivery_boy, null);

        Spinner spinner = dialogView.findViewById(R.id.adminNewDetail_spinnerDeliveryBoy);

        String[] deliveryBoys = new String[]{
                "Select delivery boy",
                "Rohit",
                "Rahul",
                "Meena",
                "Suresh"
        };

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                ctx,
                android.R.layout.simple_spinner_item,
                deliveryBoys
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        new AlertDialog.Builder(ctx)
                .setView(dialogView)
                .setCancelable(true)
                .setNegativeButton("Cancel", (d, w) -> d.dismiss())
                .setPositiveButton("Assign", (d, w) -> {

                    int pos = spinner.getSelectedItemPosition();
                    if (pos == 0) {
                        Toast.makeText(ctx, "Please select a delivery boy", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String selectedBoy = deliveryBoys[pos];

                    Toast.makeText(ctx,
                            "Order accepted and assigned to " + selectedBoy,
                            Toast.LENGTH_LONG).show();

                    // Remove from list (new orders)
                    int index = orders.indexOf(order);
                    if (index != -1) {
                        orders.remove(index);
                        notifyItemRemoved(index);
                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView tvCustomer, tvTime, tvOrderId, tvMessage, tvTotal;
        RecyclerView rvLines;
        Button btnCall, btnView, btnCancel, btnAccept;

        public VH(@NonNull View v) {
            super(v);
            imgAvatar = v.findViewById(R.id.imgAvatar);
            tvCustomer = v.findViewById(R.id.tvCustomerName);
            tvTime = v.findViewById(R.id.tvOrderTime);
            tvOrderId = v.findViewById(R.id.tvOrderId);
            tvMessage = v.findViewById(R.id.tvMessage);
            tvTotal = v.findViewById(R.id.tvTotal);
            rvLines = v.findViewById(R.id.rvOrderLines);

            btnCall = v.findViewById(R.id.btnCall);
            btnView = v.findViewById(R.id.btnView);
            btnCancel = v.findViewById(R.id.btnCancel);
            btnAccept = v.findViewById(R.id.btnAccept);
        }
    }
}
