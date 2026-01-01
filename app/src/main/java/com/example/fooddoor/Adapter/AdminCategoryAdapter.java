package com.example.fooddoor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddoor.AdminEditCategoryActivity;
import com.example.fooddoor.Modelclass.AdminCategoryModel;
import com.example.fooddoor.R;

import java.util.List;

public class AdminCategoryAdapter
        extends RecyclerView.Adapter<AdminCategoryAdapter.VH> {

    private Context context;
    private List<AdminCategoryModel> list;

    public AdminCategoryAdapter(Context context, List<AdminCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_admin_category, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        AdminCategoryModel model = list.get(position);

        h.name.setText(model.getName());

        Glide.with(context)
                .load(model.getImageUrl())
                .placeholder(R.drawable.icon_category)
                .into(h.image);

        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, AdminEditCategoryActivity.class);
            i.putExtra("CATEGORY_ID", model.getId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public VH(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.img_category);
            name = v.findViewById(R.id.tv_category_name);
        }
    }
}
