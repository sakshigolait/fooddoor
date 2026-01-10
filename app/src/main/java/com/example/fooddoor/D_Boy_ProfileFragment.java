package com.example.fooddoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class D_Boy_ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_d__boy__profile,
                container,
                false
        );

        // ================= ROW 1 : Notifications =================
        View notifications = view.findViewById(R.id.row_notifications);
        if (notifications != null) {

            TextView nText = notifications.findViewById(R.id.row_title);
            if (nText != null) {
                nText.setText("Notifications");
            }

            View root = notifications.findViewById(R.id.row_root);
            if (root != null) {
                root.setOnClickListener(v ->
                        Toast.makeText(getActivity(),
                                "Notifications Clicked",
                                Toast.LENGTH_SHORT).show()
                );
            }
        }

        // ================= ROW 2 : QR Code =================
        View qr = view.findViewById(R.id.row_qr);
        if (qr != null) {

            TextView qrText = qr.findViewById(R.id.row_title);
            if (qrText != null) {
                qrText.setText("QR Code");
            }

            View root = qr.findViewById(R.id.row_root);
            if (root != null) {
                root.setOnClickListener(v ->
                        Toast.makeText(getActivity(),
                                "QR Code Clicked",
                                Toast.LENGTH_SHORT).show()
                );
            }
        }

        // ================= ROW 3 : App Settings =================
        View appSettings = view.findViewById(R.id.row_app_settings);
        if (appSettings != null) {

            TextView aText = appSettings.findViewById(R.id.row_title);
            if (aText != null) {
                aText.setText("App Settings");
            }

            View root = appSettings.findViewById(R.id.row_root);
            if (root != null) {
                root.setOnClickListener(v ->
                        Toast.makeText(getActivity(),
                                "App Settings Clicked",
                                Toast.LENGTH_SHORT).show()
                );
            }
        }

        return view;
    }
}
