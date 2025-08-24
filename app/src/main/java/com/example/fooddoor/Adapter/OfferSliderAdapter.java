package com.example.fooddoor.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferSliderAdapter extends RecyclerView.Adapter<OfferSliderAdapter.VH> {
    private final int[] images;

    public OfferSliderAdapter(int[] images) { this.images = images; }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView iv = new ImageView(parent.getContext());
        iv.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new VH(iv);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        h.iv.setImageResource(images[pos]);
    }

    @Override public int getItemCount() { return images.length; }

    static class VH extends RecyclerView.ViewHolder {
        ImageView iv; VH(@NonNull View itemView){ super(itemView); iv = (ImageView)itemView; }
    }
}
