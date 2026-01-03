package com.example.fooddoor.fragment;

import android.app.Activity;
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
import com.example.fooddoor.R;

public class edit_profile_fragment extends Fragment {

    EditText etName, etPhone, etEmail, etAddress;
    Button btnSave;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile_fragment, container, false);

        etName = view.findViewById(R.id.etName);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        etAddress = view.findViewById(R.id.etAddress);
        btnSave = view.findViewById(R.id.btnSave);

        sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Activity.MODE_PRIVATE);

        etName.setText(sharedPreferences.getString("name", ""));
        etPhone.setText(sharedPreferences.getString("phone", ""));
        etEmail.setText(sharedPreferences.getString("email", ""));
        etAddress.setText(sharedPreferences.getString("address", ""));

        btnSave.setOnClickListener(v -> saveData());

        return view;
    }

    private void saveData() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("phone", etPhone.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("address", etAddress.getText().toString());
        editor.apply();

        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.homeframelayout, new ProfileFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}