package com.example.fooddoor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.CartData;
import com.example.fooddoor.CartModel;
import com.example.fooddoor.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    private Context context;
    private List<CartModel> list;
    private final OnCartChangeListener listener;

    public CartAdapter(Context context, List<CartModel> list, OnCartChangeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartModel item = list.get(position);

        holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice() + " rs");
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        // Plus
        holder.btnPlus.setOnClickListener(v -> {
            int q = item.getQuantity() + 1;
            item.setQuantity(q);
            holder.quantity.setText(String.valueOf(q));
            if (listener != null) listener.onCartChanged();
        });

        // Minus
        holder.btnMinus.setOnClickListener(v -> {
            int q = item.getQuantity() - 1;

            if (q > 0) {
                item.setQuantity(q);
                holder.quantity.setText(String.valueOf(q));
            } else {
                // Remove from adapter
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());

                // Update singleton
                CartData.getInstance().setCartList(list);
            }

            if (listener != null) listener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, quantity;
        Button btnPlus, btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
            quantity = itemView.findViewById(R.id.itemQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}