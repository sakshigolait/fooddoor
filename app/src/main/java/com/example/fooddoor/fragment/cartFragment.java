package com.example.fooddoor.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.CartAdapter;
import com.example.fooddoor.CartModel;
import com.example.fooddoor.Order_successFragment;
import com.example.fooddoor.PaymentFragment;
import com.example.fooddoor.SharedCartData;
import com.example.fooddoor.R;

import java.util.List;

public class cartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private CartAdapter adapter;
    private List<CartModel> list;
    private TextView tvTotalAmount;
    private Button btnPlaceOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecycler = v.findViewById(R.id.cartRecycler);
        tvTotalAmount = v.findViewById(R.id.tvTotalAmount);
        btnPlaceOrder = v.findViewById(R.id.btnPlaceOrder);

        cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        list = SharedCartData.getInstance().getCartItems();

        adapter = new CartAdapter(getContext(), list, () -> updateTotal());
        cartRecycler.setAdapter(adapter);

        updateTotal();

        btnPlaceOrder.setOnClickListener(view -> {

            // 🔹 NEW MERGE: AMOUNT CHECK
            int total = calculateTotal();
            if (total <= 0) {
                Toast.makeText(
                        getContext(),
                        "Your cart is empty",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            SharedPreferences sp =
                    requireActivity().getSharedPreferences(
                            "UserProfile",
                            getContext().MODE_PRIVATE
                    );

            String address = sp.getString("address", "");

            if (address == null || address.trim().isEmpty()) {
                Toast.makeText(
                        getContext(),
                        "Please add delivery address",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            android.app.AlertDialog.Builder builder =
                    new android.app.AlertDialog.Builder(getContext());

            View dialogView = LayoutInflater.from(getContext())
                    .inflate(R.layout.dialog_order_summary, null);

            builder.setView(dialogView);

            android.app.AlertDialog dialog = builder.create();

            TextView tvDialogAddress =
                    dialogView.findViewById(R.id.tvDialogAddress);
            TextView tvDialogTotal =
                    dialogView.findViewById(R.id.tvDialogTotal);
            Button btnCancel =
                    dialogView.findViewById(R.id.btnCancel);
            Button btnConfirm =
                    dialogView.findViewById(R.id.btnConfirm);

            tvDialogAddress.setText("Delivery Address:\n" + address);
            tvDialogTotal.setText("Total Amount: ₹" + total);

            // ✅ Cancel → ONLY close dialog
            btnCancel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });

            // ✅ Confirm → Success screen
            btnConfirm.setOnClickListener(v1 -> {

                Toast.makeText(
                        getContext(),
                        "Order Confirmed",
                        Toast.LENGTH_SHORT
                ).show();

                // ✅ ONLY go to Payment screen
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeframelayout, new PaymentFragment())
                        .addToBackStack(null)
                        .commit();

                dialog.dismiss();
            });

            dialog.show();
        });

        return v;
    }

    private void updateTotal() {
        int total = calculateTotal();
        tvTotalAmount.setText("₹" + total);
    }

    private int calculateTotal() {
        int sum = 0;
        for (CartModel c : list) {
            sum += c.getPrice() * c.getQuantity();
        }
        return sum;
    }
}
