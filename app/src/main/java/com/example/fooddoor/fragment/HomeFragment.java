package com.example.fooddoor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddoor.Adapter.FoodMenuAdapter;
import com.example.fooddoor.Adapter.OfferSliderAdapter;
import com.example.fooddoor.FoodItem;
import com.example.fooddoor.LocationActivity;
import com.example.fooddoor.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private String currentCategory = ""; // "" ka matlab sab category

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
        SearchView searchView = v.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilters(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilters(newText);
                return false;
            }
        });

        // Slider
        viewPager = v.findViewById(R.id.viewPagerOffers);
        viewPager.setAdapter(new OfferSliderAdapter(banners));

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

        // Click listeners for category filter
        tvFood.setOnClickListener(view -> filterList("Food"));
        tvDrinks.setOnClickListener(view -> filterList("Drinks"));
        tvCake.setOnClickListener(view -> filterList("Cake"));
        tvIceCream.setOnClickListener(view -> filterList("Ice Cream"));
        tvPizza.setOnClickListener(view -> filterList("Pizza"));
        tvNoodles.setOnClickListener(view -> filterList("Noodles"));

        // Location icon click
        ImageView locationIcon = v.findViewById(R.id.locationiconhomefrag);
        locationIcon.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LocationActivity.class);
            startActivity(intent);
        });

        return v;
    }

    private void filterList(String category) {
        currentCategory = category; // track karo current category
        applyFilters("");
    }

    private void applyFilters(String searchText) {
        List<FoodItem> filtered = new ArrayList<>();
        for (FoodItem item : foodList) {
            boolean matchesCategory = currentCategory.isEmpty() || item.getCategory().equalsIgnoreCase(currentCategory);
            boolean matchesSearch = item.getName().toLowerCase().contains(searchText.toLowerCase());

            if (matchesCategory && matchesSearch) {
                filtered.add(item);
            }
        }
        foodAdapter.updateList(filtered);
    }

    private List<FoodItem> getFoodItems() {
        List<FoodItem> data = new ArrayList<>();
        data.add(new FoodItem("Veg Momos", "₹119", R.drawable.momos, "Food"));
        data.add(new FoodItem("Grilled Veg Sandwich", "₹149", R.drawable.sandwich2, "Food"));
        data.add(new FoodItem("Burger", "₹129", R.drawable.burger, "Food"));
        data.add(new FoodItem("Mini Samosa", "₹59", R.drawable.samosa, "Food"));
        data.add(new FoodItem("Margherita Pizza", "₹219", R.drawable.margritapizaa, "Pizza"));
        data.add(new FoodItem("Pepperoni Pizza", "₹299", R.drawable.origanotomato, "Pizza"));
        data.add(new FoodItem("Paneer Pizza", "₹239", R.drawable.paanerpizza, "Pizza"));
        data.add(new FoodItem("Mushroom Pizza", "₹220", R.drawable.mashroompizza, "Pizza"));
        data.add(new FoodItem("Mini Margherita Pizza", "₹199", R.drawable.minipizaa, "Pizza"));
        data.add(new FoodItem("BBQ Chicken Pizza", "₹399", R.drawable.onionpizzaa, "Pizza"));
        data.add(new FoodItem("Tomato Basil Pasta", "₹249", R.drawable.spirngpasta, "Noodles"));
        data.add(new FoodItem("Spaghetti Marinara", "₹259", R.drawable.spegettiii, "Noodles"));
        data.add(new FoodItem("Ravioli", "₹349", R.drawable.squarepasta, "Noodles"));
        data.add(new FoodItem("Penne Arrabbiata", "₹350", R.drawable.pongal, "Noodles"));
        data.add(new FoodItem("Fettuccine Alfredo", "₹300", R.drawable.whitesausepasta, "Noodles"));
        data.add(new FoodItem("Fusilli Arrabbiata", "₹249", R.drawable.pasta, "Noodles"));
        data.add(new FoodItem("Cappuccino", "₹149", R.drawable.capachino, "Drinks"));
        data.add(new FoodItem("Chocolate Milkshake", "₹199", R.drawable.chocoletshake, "Drinks"));
        data.add(new FoodItem("Rose Milkshake", "₹179", R.drawable.roseshake, "Drinks"));
        data.add(new FoodItem("KesarBadam Milk", "₹129", R.drawable.kesarmilk, "Drinks"));
        data.add(new FoodItem("Oreo Milkshake", "₹189", R.drawable.oreoshake, "Drinks"));
        data.add(new FoodItem("Avocado Shake", "₹229", R.drawable.avakadoshake, "Drinks"));
        data.add(new FoodItem("Strawberry Pineapple Smoothie", "₹209", R.drawable.pinapplestrawberrymix, "Drinks"));
        data.add(new FoodItem("Blueberry Milkshake", "₹219", R.drawable.blueberryshake, "Drinks"));
        data.add(new FoodItem("Mango Milkshake", "₹159", R.drawable.mangoshake, "Drinks"));
        data.add(new FoodItem("Blackberry Mousse Cake", "₹549", R.drawable.blueberrycake, "Cake"));
        data.add(new FoodItem("Red Velvet Cake", "₹499", R.drawable.redvelvetcake, "Cake"));
        data.add(new FoodItem("Caramel Custard", "₹149", R.drawable.pudding, "Cake"));
        data.add(new FoodItem("Cherry Cupcake", "₹99", R.drawable.muffin, "Cake"));
        data.add(new FoodItem("Strawberry Pancakes", "₹249", R.drawable.pancake, "Cake"));
        data.add(new FoodItem("Strawberry Oreo Cheesecake", "₹239", R.drawable.oreocake, "Cake"));
        data.add(new FoodItem("Caramel Fruit Cheesecake", "₹279", R.drawable.honeypudding, "Cake"));
        data.add(new FoodItem("Chocolate Strawberry Cake", "₹499", R.drawable.chocolatecake, "Cake"));
        data.add(new FoodItem("Mini Chocolate Cake", "₹199", R.drawable.strawberrychocolatecake, "Cake"));
        data.add(new FoodItem("Matka Kulfi", "₹129", R.drawable.matkaicecream, "Ice Cream"));
        data.add(new FoodItem("Chocolate Brownie with Ice Cream", "₹229", R.drawable.minticecream, "Ice Cream"));
        data.add(new FoodItem("Chocolate Sundae", "₹199", R.drawable.redcherryicecream, "Ice Cream"));
        data.add(new FoodItem("Chocolate Mousse Cup", "₹149", R.drawable.cheeschocoicecream, "Ice Cream"));
        data.add(new FoodItem("Vanilla Ice Cream", "₹119", R.drawable.chockletvenellaicecream, "Ice Cream"));
        data.add(new FoodItem("Blueberry Shake", "₹179", R.drawable.blueberryicecream, "Ice Cream"));
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