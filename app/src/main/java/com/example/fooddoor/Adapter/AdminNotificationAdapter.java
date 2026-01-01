package com.example.fooddoor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Modelclass.AdminNotificationModel;
import com.example.fooddoor.R;

import java.util.List;

public class AdminNotificationAdapter
        extends RecyclerView.Adapter<AdminNotificationAdapter.ViewHolder> {

    List<AdminNotificationModel> list;

    public AdminNotificationAdapter(List<AdminNotificationModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdminNotificationModel model = list.get(position);

        holder.txtTitle.setText(model.title);
        holder.txtMessage.setText(model.message);
        holder.txtTime.setText(model.time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtMessage, txtTime;

        ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}

