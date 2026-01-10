package com.example.fooddoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.CartModel;
import com.example.fooddoor.SharedCartData;

public class PaymentFragment extends Fragment {

    private static final int UPI_PAYMENT_REQUEST = 101;

    RadioGroup rgPayment;
    RadioButton rbCOD, rbUPI;
    Button btnPayNow;

    // 🔹 cart total
    int totalAmount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        rgPayment = v.findViewById(R.id.rgPayment);
        rbCOD = v.findViewById(R.id.rbCOD);
        rbUPI = v.findViewById(R.id.rbUPI);
        btnPayNow = v.findViewById(R.id.btnPayNow);

        // 🔹 MERGE 1: Receive total from CartFragment (PRIMARY)
        if (getArguments() != null) {
            totalAmount = getArguments().getInt("totalAmount", 0);
        }

        // 🔹 MERGE 2: SAFETY FALLBACK (real-world fix)
        // Agar bundle miss ho gaya ho to cart se total nikal lo
        if (totalAmount <= 0) {
            for (CartModel c : SharedCartData.getInstance().getCartItems()) {
                totalAmount += c.getPrice() * c.getQuantity();
            }
        }

        btnPayNow.setOnClickListener(view -> {

            int selectedId = rgPayment.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(
                        getContext(),
                        "Please select a payment method",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // 🔹 CASE 1: CASH ON DELIVERY
            if (selectedId == R.id.rbCOD) {

                Toast.makeText(
                        getContext(),
                        "Order placed with Cash on Delivery",
                        Toast.LENGTH_SHORT
                ).show();

                SharedCartData.getInstance().getCartItems().clear();

                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeframelayout, new Order_successFragment())
                        .addToBackStack(null)
                        .commit();
            }

            // 🔹 CASE 2: REAL UPI PAYMENT
            else if (selectedId == R.id.rbUPI) {

                if (totalAmount <= 0) {
                    Toast.makeText(
                            getContext(),
                            "Invalid amount",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                startUpiPayment(String.valueOf(totalAmount));
            }
        });

        return v;
    }

    // 🔹 REAL UPI INTENT
    private void startUpiPayment(String amount) {

        String upiId = "9156859060@ybl"; // demo UPI ID
        String name = "FoodDoor";
        String note = "Food Order Payment";

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

        Intent upiIntent = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(upiIntent, "Pay with UPI");

        if (chooser.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(chooser, UPI_PAYMENT_REQUEST);
        } else {
            Toast.makeText(
                    getContext(),
                    "No UPI app found",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // 🔹 UPI PAYMENT RESULT
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPI_PAYMENT_REQUEST && data != null) {

            String response = data.getStringExtra("response");

            if (response != null && response.toLowerCase().contains("success")) {

                Toast.makeText(
                        getContext(),
                        "Payment Successful",
                        Toast.LENGTH_SHORT
                ).show();

                SharedCartData.getInstance().getCartItems().clear();

                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeframelayout, new Order_successFragment())
                        .addToBackStack(null)
                        .commit();

            } else {
                Toast.makeText(
                        getContext(),
                        "Payment Failed or Cancelled",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}
