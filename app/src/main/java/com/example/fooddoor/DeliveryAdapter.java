package com.example.fooddoor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    Context context;
    ArrayList<DeliveryModel> list;

    public DeliveryAdapter(Context context, ArrayList<DeliveryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.d_boy_item_delivery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryModel model = list.get(position);

        holder.txtOrderId.setText("Order ID: " + model.getOrderId());
        holder.txtPayment.setText("Payment Method: " + model.getPaymentMethod());
        holder.txtTotalPayment.setText("Total Payment: " + model.getTotalPayment());
        holder.txtPickup.setText("Street: " + model.getPickupAddress());
        holder.txtDrop.setText("Street: " + model.getDropAddress());
        holder.txtTime.setText(model.getTime());


        // 🔴 MOST IMPORTANT PART
        holder.btnNext.setOnClickListener(v -> {

            Intent intent = new Intent(context, D_Boy_MyLocationActivity.class);

            intent.putExtra("DROP_LAT", model.getDropLat());
            intent.putExtra("DROP_LNG", model.getDropLng());

            Log.d("SEND_DEST",
                    "Lat=" + model.getDropLat() +
                            " Lng=" + model.getDropLng());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtOrderId, txtPayment, txtTotalPayment, txtPickup, txtDrop, txtTime;
        ImageView btnNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderId = itemView.findViewById(R.id.txtOrderID);
            txtPayment = itemView.findViewById(R.id.txtPayment);
            txtTotalPayment = itemView.findViewById(R.id.txtTotalPayment);
            txtPickup = itemView.findViewById(R.id.txtPickup);
            txtDrop = itemView.findViewById(R.id.txtDrop);
            txtTime = itemView.findViewById(R.id.txtTime);
            btnNext = itemView.findViewById(R.id.btnNext);
        }
    }
}