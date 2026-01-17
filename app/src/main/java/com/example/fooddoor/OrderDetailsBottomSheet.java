package com.example.fooddoor;
import com.example.fooddoor.DeliveryModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fooddoor.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class OrderDetailsBottomSheet extends BottomSheetDialogFragment {


    public static OrderDetailsBottomSheet newInstance(DeliveryModel model) {

        OrderDetailsBottomSheet fragment = new OrderDetailsBottomSheet();

        Bundle args = new Bundle();
        args.putSerializable("order_data", model); // model Serializable hona chahiye
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_order_details_bottom_sheet, container, false);

        Button btnAccept = view.findViewById(R.id.btn_Accept);
        Button btnReject = view.findViewById(R.id.btn_Reject);

        btnReject.setOnClickListener(v -> dismiss());
        btnAccept.setOnClickListener(v -> {
            DeliveryModel model =
                    (DeliveryModel) getArguments().getSerializable("order_data");

            if (model == null) return;

            Intent intent = new Intent(requireContext(),D_Boy_MyLocationActivity.class);
            intent.putExtra("pickup_lat", model.getPickupLat());
            intent.putExtra("pickup_lng", model.getPickupLng());
            intent.putExtra("drop_lat", model.getDropLat());
            intent.putExtra("drop_lng", model.getDropLng());
            intent.putExtra("show_route", true);

            startActivity(intent);
            dismiss();
        });

        return view;
    }


}