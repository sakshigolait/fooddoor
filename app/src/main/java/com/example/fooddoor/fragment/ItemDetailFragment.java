package com.example.fooddoor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.CartModel;
import com.example.fooddoor.R;
import com.example.fooddoor.SharedCartData;


public class ItemDetailFragment extends Fragment {


    @Nullable
    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_detail, container, false);
        TextView tvName = v.findViewById(R.id.tvItemName);
        TextView tvPrice = v.findViewById(R.id.tvItemPrice);
        ImageView ivImage = v.findViewById(R.id.ivItemImage);
        Button btnAddToCart = v.findViewById(R.id.btnAddToCart);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tvName.setText(bundle.getString("name"));
            tvPrice.setText(bundle.getString("price"));
            ivImage.setImageResource(bundle.getInt("image"));
        }

        btnAddToCart.setOnClickListener(vw -> {
            // Yaha cart me add karne ka logic aayega (CartData use karke)

            String name = getArguments().getString("name");
            String priceStr = getArguments().getString("price").replace("₹","");
            int price = Integer.parseInt(priceStr);
            int image = getArguments().getInt("image");

            CartModel item = new CartModel(name, 1, price, image);
            SharedCartData.getInstance().addItem(item);

            Toast.makeText(getContext(), name + " added to cart", Toast.LENGTH_SHORT).show();
        });


        return v;
    }
}
