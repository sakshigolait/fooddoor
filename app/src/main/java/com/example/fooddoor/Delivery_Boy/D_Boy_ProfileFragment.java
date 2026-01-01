package com.example.fooddoor.Delivery_Boy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.LoginActivity;
import com.example.fooddoor.R;

public class D_Boy_ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public D_Boy_ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static D_Boy_ProfileFragment newInstance(String param1, String param2) {
        D_Boy_ProfileFragment fragment = new D_Boy_ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void showLogoutDialog() {

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.d_boy_logout_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnLogout = dialog.findViewById(R.id.DboybtnLogout);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnLogout.setOnClickListener(v -> {
            dialog.dismiss();
            // TODO: Replace with your login screen
            Intent i = new Intent(requireActivity(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            requireActivity().finish();

        });

        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Click listeners
        view.findViewById(R.id.DboybtnNotifications).setOnClickListener(v ->
                Toast.makeText(getActivity(), "Notifications Clicked", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.DboybtnShare).setOnClickListener(v ->
                Toast.makeText(getActivity(), "Share & Earn Clicked", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.DboybtnSettings).setOnClickListener(v ->
                Toast.makeText(getActivity(), "Settings Clicked", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.DboybtnLogout).setOnClickListener(v ->
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show());

        return view;

    }

}





