package com.example.fooddoor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import com.example.fooddoor.fragment.ProfileFragment;

public class edit_profile_fragment extends Fragment {

    EditText etName, etPhone, etEmail, etAddress;
    Button btnSave;
    SharedPreferences sharedPreferences;

    Spinner spinnerAddressType;

    // 🔹 ADDED (spinner auto trigger fix)
    boolean isUserClick = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile_fragment, container, false);

        etName = view.findViewById(R.id.etName);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        etAddress = view.findViewById(R.id.etAddress);
        btnSave = view.findViewById(R.id.btnSave);
        spinnerAddressType = view.findViewById(R.id.spinnerAddressType);

        sharedPreferences = requireActivity()
                .getSharedPreferences("UserProfile", Activity.MODE_PRIVATE);

        etName.setText(sharedPreferences.getString("name", ""));
        etPhone.setText(sharedPreferences.getString("phone", ""));
        etEmail.setText(sharedPreferences.getString("email", ""));
        etAddress.setText(sharedPreferences.getString("address", ""));

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.address_options,
                        android.R.layout.simple_spinner_item
                );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinnerAddressType.setAdapter(adapter);

        etAddress.setEnabled(false);

        // 🔹 ADDED (detect real user click)
        spinnerAddressType.setOnTouchListener((v, e) -> {
            isUserClick = true;
            return false;
        });

        // 🔹 UPDATED PART (Map open on Current Location)
        spinnerAddressType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {

                        if (!isUserClick) return;

                        if (position == 0) {
                            // Current Location → Map open
                            etAddress.setEnabled(false);
                            Intent intent =
                                    new Intent(getActivity(), LocationActivity.class);
                            startActivity(intent);

                        } else if (position == 1) {
                            // Enter Manually
                            etAddress.setEnabled(true);
                            etAddress.setText("");
                        }

                        isUserClick = false;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                }

        );

        btnSave.setOnClickListener(v -> saveData());

        return view;
    }

    // 🔹 ADDED (address auto-fill when coming back from map)
    @Override
    public void onResume() {
        super.onResume();

        String address = sharedPreferences.getString("address", "");
        if (!address.isEmpty()) {
            etAddress.setText(address);
        }
    }

    private void saveData() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("phone", etPhone.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("address", etAddress.getText().toString());
        editor.apply();

        FragmentTransaction ft =
                getParentFragmentManager().beginTransaction();
        ft.replace(R.id.homeframelayout, new ProfileFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
