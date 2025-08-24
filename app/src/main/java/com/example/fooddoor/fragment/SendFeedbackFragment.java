package com.example.fooddoor.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.R;

public class SendFeedbackFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_send_feedback, container, false);

        ImageView btnBack = view.findViewById(R.id.btnBack);
        EditText etFeedback = view.findViewById(R.id.etFeedback);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);

        // Back arrow -> previous screen
        btnBack.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());

        // Submit button
        btnSubmit.setOnClickListener(v -> {
            String feedback = etFeedback.getText().toString().trim();

            if (TextUtils.isEmpty(feedback)) {
                Toast.makeText(requireContext(),
                        "Please enter your feedback", Toast.LENGTH_SHORT).show();
            } else {
                // yaha tum API call / Firebase etc laga sakti ho
                Toast.makeText(requireContext(),
                        "Feedback submitted. Thank you!", Toast.LENGTH_SHORT).show();
                etFeedback.setText("");
            }
        });

        return view;
    }
}
