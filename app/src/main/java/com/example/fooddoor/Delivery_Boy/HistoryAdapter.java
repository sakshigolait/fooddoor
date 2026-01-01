package com.example.fooddoor.Delivery_Boy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<HistoryModel> list;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryModel model = list.get(position);

        holder.txtOrderId.setText("Order ID: " + model.orderId);
        holder.txtPaymentMethod.setText("Payment Method: " + model.paymentMethod);
        holder.txtPaymentAmount.setText("Total Payment: " + model.amount);
        holder.txtStatus.setText("Order Status: " + model.status);

        // Set status colors
        if (model.status.equalsIgnoreCase("Delivered")) {
            holder.txtStatus.setTextColor(Color.parseColor("#2ECC71")); // green
        } else if (model.status.equalsIgnoreCase("Pending")) {
            holder.txtStatus.setTextColor(Color.parseColor("#E67E22")); // orange
        } else {
            holder.txtStatus.setTextColor(Color.parseColor("#E74C3C")); // red
        }
    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderId, txtPaymentMethod, txtPaymentAmount, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtPaymentMethod = itemView.findViewById(R.id.txtPaymentMethod);
            txtPaymentAmount = itemView.findViewById(R.id.txtPaymentAmount);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}