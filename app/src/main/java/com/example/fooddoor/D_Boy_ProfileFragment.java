package com.example.fooddoor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.LoginActivity;
import com.example.fooddoor.Modelclass.QR_code_Activity;
import com.example.fooddoor.R;

public class D_Boy_ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public D_Boy_ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_d__boy__profile, container, false);

        // ✅ SAFE WAY: pehle view lo, phir listener lagao
        View llProfileEdit = view.findViewById(R.id.llProfileEdit);
        View btnNotifications = view.findViewById(R.id.tvNotificationEdit);
        View btnQR = view.findViewById(R.id.tvQR);
        View btnSettings = view.findViewById(R.id.tvSettingsEdit);
        View btnDeleteAcc =view.findViewById(R.id.tvDeleteAcc);
        View btnLogOut = view.findViewById(R.id.tvLogOut);
if (llProfileEdit != null) {
    llProfileEdit.setOnClickListener(v -> {
        Intent intent = new Intent(getActivity(), D_Boy_ProfileData.class);
        startActivity(intent);
    });

    if (btnNotifications != null) {
        btnNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), D_Boy_NotificationActivity.class);
            startActivity(intent);
        });
        if (btnQR!= null) {
            btnQR.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), QR_code_Activity.class);
                startActivity(intent);
            });

            if (btnSettings != null) {
                btnSettings.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), D_Boy_settingActivity.class);
                    startActivity(intent);
                });
                if (btnDeleteAcc != null) {
                    btnDeleteAcc.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    });

                    if (btnLogOut != null) {
                        btnLogOut.setOnClickListener(v -> showLogoutDialog());
                    }
                }
            }
        }
    }
}
        return view;
    }

            private void showLogoutDialog () {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.d_boy_logout_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnLogout = dialog.findViewById(R.id.btnLogout);

                btnCancel.setOnClickListener(v -> dialog.dismiss());

                btnLogout.setOnClickListener(v -> {
                    dialog.dismiss();

                    // TODO: Replace with your login screen
                    Intent i = new Intent(requireContext(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                });

                dialog.show();
            }

        private void finish () {
    }
}