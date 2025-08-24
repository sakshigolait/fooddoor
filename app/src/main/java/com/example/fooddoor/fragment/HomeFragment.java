package com.example.fooddoor.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddoor.Adapter.FoodMenuAdapter;
import com.example.fooddoor.Adapter.OfferSliderAdapter;
import com.example.fooddoor.FoodItem;
import com.example.fooddoor.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private RecyclerView rv;
    private FoodMenuAdapter foodAdapter;
    private List<FoodItem> foodList; // full data

    private final Handler sliderHandler = new Handler();
    private int[] banners = {R.drawable.coffeeoffer, R.drawable.cakeoffer, R.drawable.icecreamoffer,
            R.drawable.pizzaoffer,R.drawable.burgeroffer,R.drawable.watermelonoffer};

    private final Runnable sliderRunnable = new Runnable() {
        @Override public void run() {
            if (viewPager == null) return;
            int next = (viewPager.getCurrentItem() + 1) % banners.length;
            viewPager.setCurrentItem(next, true);
            sliderHandler.postDelayed(this, 3000); // auto every 3s
        }
    };

    // Category TextViews
    private TextView tvFood, tvDrinks, tvCake, tvIceCream, tvPizza, tvNoodles;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Slider
        viewPager = v.findViewById(R.id.viewPagerOffers);
        viewPager.setAdapter(new OfferSliderAdapter(banners));

      //  TabLayout dots = v.findViewById(R.id.tabDots);
        //new TabLayoutMediator(dots, viewPager, (tab, position) -> {}).attach();

        // Food grid
        rv = v.findViewById(R.id.rvFoodMenu);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setHasFixedSize(true);


        // Full food list
        foodList = getFoodItems();
        foodAdapter = new FoodMenuAdapter(foodList);
        rv.setAdapter(foodAdapter);

        // Category IDs
        tvFood = v.findViewById(R.id.tvFood);
        tvDrinks = v.findViewById(R.id.tvDrinks);
        tvCake = v.findViewById(R.id.tvCake);
        tvIceCream = v.findViewById(R.id.tvIceCream);
        tvPizza = v.findViewById(R.id.tvPizza);
        tvNoodles = v.findViewById(R.id.tvNoodles);

        // Click listeners
        tvFood.setOnClickListener(view -> filterList("Food"));
        tvDrinks.setOnClickListener(view -> filterList("Drinks"));
        tvCake.setOnClickListener(view -> filterList("Cake"));
        tvIceCream.setOnClickListener(view -> filterList("Ice Cream"));
        tvPizza.setOnClickListener(view -> filterList("Pizza"));
        tvNoodles.setOnClickListener(view -> filterList("Noodles"));

        return v;
    }

    private void filterList(String category) {
        List<FoodItem> filtered = new ArrayList<>();
        for (FoodItem item : foodList) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filtered.add(item);
            }
        }
        foodAdapter.updateList(filtered);
    }

    private List<FoodItem> getFoodItems() {
        List<FoodItem> data = new ArrayList<>();
        data.add(new FoodItem("Burger", "₹120", R.drawable.burger, "Food"));
        data.add(new FoodItem("Sandwich", "₹90", R.drawable.sandwich2, "Food"));
        data.add(new FoodItem("Pasta", "₹180", R.drawable.samosa, "Food"));
        data.add(new FoodItem("Pasta", "₹180", R.drawable.momos, "Food"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.margritapizaa, "Pizza"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.origanotomato, "Pizza"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.paanerpizza, "Pizza"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.mashroompizza, "Pizza"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.minipizaa, "Pizza"));
        data.add(new FoodItem("Pizza", "₹220", R.drawable.onionpizzaa, "Pizza"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.spirngpasta, "Noodles"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.spegettiii, "Noodles"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.squarepasta, "Noodles"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.pongal, "Noodles"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.whitesausepasta, "Noodles"));
        data.add(new FoodItem("Noodles", "₹150", R.drawable.pasta, "Noodles"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.capachino, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.chocoletshake, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.roseshake, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.kesarmilk, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.oreoshake, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.avakadoshake, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.pinapplestrawberrymix, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.blueberryshake, "Drinks"));
        data.add(new FoodItem("Coke", "₹60", R.drawable.mangoshake, "Drinks"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.blueberrycake, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.redvelvetcake, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.pudding, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.muffin, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.pancake, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.oreocake, "Cake"));
        data.add(new FoodItem("Chocolate Cake", "₹250", R.drawable.honeypudding, "Cake"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.chocolatecake, "Cake"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.strawberrychocolatecake, "Cake"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.matkaicecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.minticecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.redcherryicecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.cheeschocoicecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.chockletvenellaicecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹120", R.drawable.blueberryicecream, "Ice Cream"));
        return data;
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}
