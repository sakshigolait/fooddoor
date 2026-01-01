package com.example.fooddoor.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.FoodMenuAdapter;
import com.example.fooddoor.FavManager;
import com.example.fooddoor.FoodItem;
import com.example.fooddoor.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    RecyclerView rvFav;
    TextView emptyText;
    FoodMenuAdapter adapter;
    List<FoodItem> favList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        rvFav = view.findViewById(R.id.favourite);
        emptyText = view.findViewById(R.id.txtQty);

        rvFav.setLayoutManager(new LinearLayoutManager(getContext()));

        loadFavouriteItems();

        return view;
    }

    // ⭐ Load only current user favourites ⭐
    private void loadFavouriteItems() {
        Context ctx = requireContext();

        favList.clear();
        favList.addAll(FavManager.getFavouriteItems(ctx));

        if (favList.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            rvFav.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            rvFav.setVisibility(View.VISIBLE);

            adapter = new FoodMenuAdapter(favList);
            rvFav.setAdapter(adapter);
        }
    }
}
