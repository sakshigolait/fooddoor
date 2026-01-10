package com.example.fooddoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.R;
import com.example.fooddoor.SharedCartData;

public class Order_successFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_success, container, false);

        Button btnGoHome = v.findViewById(R.id.btnGoHome);

        // 🔹 Clear cart after successful order
        SharedCartData.getInstance().getCartItems().clear();

        btnGoHome.setOnClickListener(view -> {
            requireActivity().getSupportFragmentManager()
                    .popBackStack(null,
                            getParentFragmentManager().POP_BACK_STACK_INCLUSIVE);
        });

        return v;
    }
}
