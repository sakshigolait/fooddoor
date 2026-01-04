package com.example.fooddoor.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.R;
import com.example.fooddoor.javaClass.OrderLine;

import java.text.DecimalFormat;
import java.util.List;

public class AdminOrderLineAdapter extends RecyclerView.Adapter<AdminOrderLineAdapter.Holder> {

    private List<OrderLine> list;
    private DecimalFormat df = new DecimalFormat("#.00");

    public AdminOrderLineAdapter(List<OrderLine> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_order_line, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int pos) {
        OrderLine line = list.get(pos);
        h.tvName.setText(line.name);
        h.tvQty.setText("Qty: " + line.qty);
        h.tvPrice.setText("$" + df.format(line.getLineTotal()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView tvName, tvQty, tvPrice;

        public Holder(@NonNull View v) {
            super(v);
            tvName = v.findViewById(R.id.tvItemName);
            tvQty = v.findViewById(R.id.tvQty);
            tvPrice = v.findViewById(R.id.tvPrice);
        }
    }
}
