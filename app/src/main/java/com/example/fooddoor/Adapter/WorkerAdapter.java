package com.example.fooddoor.Adapter;
import android.content.Context;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Modelclass.WorkerModel;
import com.example.fooddoor.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private ArrayList<WorkerModel> workerList;
    private Context context;

    public WorkerAdapter(ArrayList<WorkerModel> workerList, Context context) {
        this.workerList = workerList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        WorkerModel worker = workerList.get(position);
        holder.txtName.setText(worker.getName());
        holder.txtRole.setText(worker.getRole());

        holder.btnUpdate.setOnClickListener(v -> {
            Toast.makeText(context, "Update " + worker.getName(), Toast.LENGTH_SHORT).show();
            // TODO: Open update dialog if needed
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Worker")
                    .setMessage("Are you sure you want to delete " + worker.getName() + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        workerList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, workerList.size());
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    static class WorkerViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtRole;
        MaterialButton btnUpdate, btnDelete;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRole = itemView.findViewById(R.id.txtRole);
            btnUpdate = itemView.findViewById(R.id.btnupdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}