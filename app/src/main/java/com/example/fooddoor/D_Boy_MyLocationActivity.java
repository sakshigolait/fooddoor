package com.example.fooddoor;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fooddoor.databinding.ActivityDboyMyLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class D_Boy_MyLocationActivity extends FragmentActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityDboyMyLocationBinding binding;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDboyMyLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        // Permission check
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        }

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {

                    if (location == null) {
                        return;
                    }

                    // 📍 CURRENT LOCATION
                    LatLng currentLatLng = new LatLng(
                            location.getLatitude(),
                            location.getLongitude()
                    );

                    double dropLat = getIntent().getDoubleExtra("DROP_LAT", 0);
                    double dropLng = getIntent().getDoubleExtra("DROP_LNG", 0);

                    Log.d("DESTINATION","Lat: "+dropLat+"Lng:"+dropLng);

                    if (dropLat == 0 || dropLng == 0) {
                        Toast.makeText(this, "Destination not received", Toast.LENGTH_LONG).show();
                        return;
                    }

                    LatLng destination = new LatLng(dropLat, dropLng);

                    // 🔵 MARKERS
                    mMap.addMarker(new MarkerOptions()
                            .position(currentLatLng)
                            .title("You"));

                    mMap.addMarker(new MarkerOptions()
                            .position(destination)
                            .title("Destination"));

                    // 🔴 POLYLINE
                    mMap.addPolyline(new PolylineOptions()
                            .add(currentLatLng, destination)
                            .width(10f)
                            .color(Color.BLUE));

                    mMap.addMarker(new MarkerOptions()
                            .position(currentLatLng)
                            .title("Delivery Boy")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.delivery_boy)));

                    // 🎯 CAMERA – SHOW BOTH POINTS
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(currentLatLng)
                            .include(destination)
                            .build();

                    mMap.animateCamera(
                            CameraUpdateFactory.newLatLngBounds(bounds, 150)
                    );
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101 &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED &&
                    mMap != null) {

                mMap.setMyLocationEnabled(true);
            }
        }
    }
}