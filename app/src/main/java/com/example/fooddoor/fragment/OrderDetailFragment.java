package com.example.fooddoor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.R;

public class OrderDetailFragment extends Fragment {

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orderdetail, container, false);

        ImageView img = view.findViewById(R.id.imgOrderDetail);
        TextView tvName   = view.findViewById(R.id.txtNameDetail);    // ⭐ new
        TextView tvStatus = view.findViewById(R.id.txtStatusDetail);
        TextView tvDate   = view.findViewById(R.id.txtDateDetail);
        TextView tvQty    = view.findViewById(R.id.txtQtyDetail);

        // 👉 Data aa raha hai Adapter se
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name   = bundle.getString("name", "");
            String status = bundle.getString("status", "");
            String date   = bundle.getString("date", "");
            int qty       = bundle.getInt("qty", 0);
            int imageRes  = bundle.getInt("image", 0);

            tvName.setText("Item: " + name);
            tvStatus.setText("Status: " + status);
            tvDate.setText("Date: " + date);
            tvQty.setText("Quantity: " + qty);

            if (imageRes != 0) {
                img.setImageResource(imageRes);
            }
        }

        return view;
    }
}