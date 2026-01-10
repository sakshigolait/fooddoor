package com.example.fooddoor;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends FragmentActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_REQUEST_CODE = 101;

    String finalAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button btnConfirmLocation = findViewById(R.id.btnConfirmLocation);
        btnConfirmLocation.setOnClickListener(v -> {

            if (!finalAddress.isEmpty()) {
                SharedPreferences sp =
                        getSharedPreferences("UserProfile", MODE_PRIVATE);
                sp.edit()
                        .putString("address", finalAddress)
                        .apply();

                // 🔥 MERGED CODE (ONLY ADDITION)
                fusedLocationClient.removeLocationUpdates(locationCallback);
                finish(); // ⬅️ Activity band hogi ONLY button click pe
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE
            );
            return;
        }

        mMap.setMyLocationEnabled(true);

        // 🔥 EXACT LOCATION REQUEST
        LocationRequest locationRequest =
                LocationRequest.create();
        locationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(1000);

        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
        );
    }

    // 🔥 REAL TIME LOCATION CALLBACK
    LocationCallback locationCallback =
            new LocationCallback() {
                @Override
                public void onLocationResult(
                        @NonNull LocationResult locationResult) {

                    if (locationResult == null) return;

                    LatLng current =
                            new LatLng(
                                    locationResult.getLastLocation().getLatitude(),
                                    locationResult.getLastLocation().getLongitude()
                            );

                    mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(current, 17)
                    );

                    Geocoder geocoder =
                            new Geocoder(
                                    LocationActivity.this,
                                    Locale.getDefault()
                            );
                    try {
                        List<Address> addresses =
                                geocoder.getFromLocation(
                                        current.latitude,
                                        current.longitude,
                                        1
                                );

                        if (!addresses.isEmpty()) {
                            Address a = addresses.get(0);

                            String street = a.getThoroughfare();
                            if (street == null) street = a.getSubLocality();

                            String city = a.getLocality();
                            if (city == null) city = a.getSubAdminArea();

                            String state = a.getAdminArea();
                            String pincode = a.getPostalCode();

                            if (street == null) street = "";
                            if (city == null) city = "";
                            if (state == null) state = "";
                            if (pincode == null) pincode = "";

                            finalAddress =
                                    street + ", " +
                                            city + ", " +
                                            state + " - " +
                                            pincode;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if (requestCode == LOCATION_REQUEST_CODE
                && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {

            onMapReady(mMap);
        }
    }
}
