package com.example.fooddoor.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.FavManager;
import com.example.fooddoor.FoodItem;
import com.example.fooddoor.R;
import com.example.fooddoor.fragment.ItemDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.VH> {

    public static List<FoodItem> fullList = new ArrayList<>();

    private List<FoodItem> items;

    public FoodMenuAdapter(List<FoodItem> items) {
        this.items = items;
        fullList = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        FoodItem f = items.get(pos);

        // тнР Yaha har item register ho raha hai (chahe cake ho, drink ho, etc.)
        FavManager.registerItem(f);

        h.name.setText(f.getName());
        h.price.setText(f.getPrice());
        h.img.setImageResource(f.getImageRes());

        Context ctx = h.itemView.getContext();

        String itemId = f.getName(); // name as unique id

        boolean isFav = FavManager.isFavourite(ctx, itemId);
        h.imgFav.setImageResource(
                isFav ? R.drawable.heart_filled : R.drawable.heart
        );

        h.imgFav.setOnClickListener(v -> {
            FavManager.toggleFavourite(ctx, itemId);

            boolean nowFav = FavManager.isFavourite(ctx, itemId);
            h.imgFav.setImageResource(
                    nowFav ? R.drawable.heart_filled : R.drawable.heart
            );
        });

        h.itemView.setOnClickListener(v -> {
            ItemDetailFragment fragment = new ItemDetailFragment();

            Bundle bundle = new Bundle();
            bundle.putString("name", f.getName());
            bundle.putString("price", f.getPrice());
            bundle.putInt("image", f.getImageRes());
            fragment.setArguments(bundle);

            ((FragmentActivity) v.getContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeframelayout, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<FoodItem> filtered) {
        this.items = filtered;
        fullList = filtered;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img, imgFav;
        TextView name, price;

        VH(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgFood);
            name = v.findViewById(R.id.txtName);
            price = v.findViewById(R.id.txtPrice);
            imgFav = v.findViewById(R.id.imgFav);
        }
    }
}