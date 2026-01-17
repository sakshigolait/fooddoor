package com.example.fooddoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class D_Boy_HomeFragment extends Fragment {

    private MapView mapView;
    private GoogleMap googleMap;
    private TextView btnAvailableDeliveries;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_d__boy__home,
                container,
                false
        );

        // ✅ VERY IMPORTANT: initialize views using "view"
        btnAvailableDeliveries = view.findViewById(R.id.txtAvailableDel);
        mapView = view.findViewById(R.id.mapView);

        // 🛑 Safety check
        if (btnAvailableDeliveries == null) {
            throw new RuntimeException(
                    "btnAvailableDeliveries is NULL. Check fragment_d__boy__home.xml ID"
            );
        }

        // Button click
        btnAvailableDeliveries.setOnClickListener(v -> {
            Intent intent = new Intent(
                    getActivity(),
                    D_Boy_AvailableDeliveries.class
            );
            startActivity(intent);
        });

        // Map setup
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap map) {
                googleMap = map;

                if (getArguments() != null) {

                    double pickupLat = getArguments().getDouble("pickup_lat");
                    double pickupLng = getArguments().getDouble("pickup_lng");
                    double dropLat = getArguments().getDouble("drop_lat");
                    double dropLng = getArguments().getDouble("drop_lng");

                    LatLng pickup = new LatLng(pickupLat, pickupLng);
                    LatLng drop = new LatLng(dropLat, dropLng);

                    map.addMarker(new MarkerOptions()
                            .position(pickup)
                            .title("Pickup Location"));

                    map.addMarker(new MarkerOptions()
                            .position(drop)
                            .title("Drop Location"));

                    map.addPolyline(new PolylineOptions()
                            .add(pickup, drop)
                            .width(10)
                            .color(Color.BLUE));

                    map.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(pickup, 13f)
                    );
                }

                googleMap.getUiSettings().setZoomControlsEnabled(true);

                // Default location: Maharashtra
                LatLng location = new LatLng(19.7515, 75.7139);
                googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(location, 13)
                );
            }
        });

        return view;
    }

    // 🔁 MapView lifecycle (mandatory)
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}