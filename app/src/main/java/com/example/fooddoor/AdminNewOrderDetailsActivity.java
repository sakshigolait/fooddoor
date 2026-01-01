package com.example.fooddoor;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddoor.Adapter.AdminOrderLineAdapter;
import com.example.fooddoor.R;
import com.example.fooddoor.javaClass.Order;

import java.io.File;
import java.text.DecimalFormat;

public class AdminNewOrderDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ADMIN_NEW_ORDER_DETAILS = "extra_admin_new_order_details";

    private Order detailsOrder;
    private DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order_details);

        // ✅ YAHAN AB Order me cast kar rahe hain, Model me nahi
        detailsOrder = (Order) getIntent()
                .getSerializableExtra(EXTRA_ADMIN_NEW_ORDER_DETAILS);

        if (detailsOrder == null) {
            finish();
            return;
        }

        // Views
        ImageView back = findViewById(R.id.adminNewDetail_buttonBack);
        ImageView avatar = findViewById(R.id.adminNewDetail_imageAvatar);

        TextView tvCustomer = findViewById(R.id.adminNewDetail_textCustomerName);
        TextView tvTime = findViewById(R.id.adminNewDetail_textTime);
        TextView tvOrderId = findViewById(R.id.adminNewDetail_textOrderId);
        TextView tvPhone = findViewById(R.id.adminNewDetail_textPhone);
        TextView tvEmail = findViewById(R.id.adminNewDetail_textEmail);
        TextView tvAddress = findViewById(R.id.adminNewDetail_textAddress);
        TextView tvMessage = findViewById(R.id.adminNewDetail_textMessage);

        TextView tvSubtotal = findViewById(R.id.adminNewDetail_textSubtotal);
        TextView tvDelivery = findViewById(R.id.adminNewDetail_textDeliveryFee);
        TextView tvTax = findViewById(R.id.adminNewDetail_textServiceTax);
        TextView tvDiscount = findViewById(R.id.adminNewDetail_textDiscount);
        TextView tvTotal = findViewById(R.id.adminNewDetail_textTotal);

        RecyclerView rvItems = findViewById(R.id.adminNewDetail_itemsRecyclerView);

        Button btnCall = findViewById(R.id.adminNewDetail_buttonCall);
        Button btnEmail = findViewById(R.id.adminNewDetail_buttonEmail);

        Button btnCancel = findViewById(R.id.adminNewDetail_buttonCancelOrder);
        Button btnAccept = findViewById(R.id.adminNewDetail_buttonAcceptOrder);

        // ✅ Data set in UI from Order object
        tvCustomer.setText(detailsOrder.customerName);
        tvTime.setText(detailsOrder.time);
        tvOrderId.setText("Order id: " + detailsOrder.orderId);
        tvMessage.setText(detailsOrder.message != null ? detailsOrder.message : "");

        if (detailsOrder.phone != null) tvPhone.setText(detailsOrder.phone);
        if (detailsOrder.email != null) tvEmail.setText(detailsOrder.email);
        if (detailsOrder.address != null) tvAddress.setText(detailsOrder.address);

        if (detailsOrder.avatarPath != null) {
            Glide.with(this)
                    .load(Uri.fromFile(new File(detailsOrder.avatarPath)))
                    .into(avatar);
        }

        // ✅ Items list (reuse your AdminOrderLineAdapter)
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new AdminOrderLineAdapter(detailsOrder.lines));

        // ✅ Totals
        double subtotal = detailsOrder.getTotal();
        double delivery = 0;
        double tax = 0;
        double discount = 0;
        double total = subtotal + delivery + tax - discount;

        tvSubtotal.setText("Subtotal: $" + df.format(subtotal));
        tvDelivery.setText("Delivery Fee: $" + df.format(delivery));
        tvTax.setText("Service tax (0%): $" + df.format(tax));
        tvDiscount.setText("Discount (0%): $" + df.format(discount));
        tvTotal.setText("Total: $" + df.format(total));

        // ✅ Back
        back.setOnClickListener(v -> onBackPressed());

        // ✅ Call
        btnCall.setOnClickListener(v -> {
            String phone = tvPhone.getText().toString();
            if (!phone.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
            } else {
                Toast.makeText(this, "Phone not available", Toast.LENGTH_SHORT).show();
            }
        });

        // ✅ Email
        btnEmail.setOnClickListener(v -> {
            String email = tvEmail.getText().toString();
            if (!email.isEmpty()) {
                Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(Intent.createChooser(mail, "Send email"));
            } else {
                Toast.makeText(this, "Email not available", Toast.LENGTH_SHORT).show();
            }
        });



        // ✅ Cancel / Accept (Firebase / DB logic baad me)
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Order Cancelled", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnAccept.setOnClickListener(v -> {
            showDeliveryBoyDialog();
        });

    }

    private void showDeliveryBoyDialog() {
        // 1) Dialog view inflate
        android.view.LayoutInflater inflater = android.view.LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_admin_new_detail_delivery_boy, null);

        Spinner spinner = dialogView.findViewById(R.id.adminNewDetail_spinnerDeliveryBoy);

        // 2) Spinner ke options (baad me Firebase se aa sakte hain)
        String[] deliveryBoys = new String[]{
                "Select delivery boy",
                "Rohit",
                "Rahul",
                "Meena",
                "Suresh"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                deliveryBoys
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 3) Dialog build
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("Assign", (dialog, which) -> {
                    int pos = spinner.getSelectedItemPosition();
                    if (pos == 0) {
                        Toast.makeText(this, "Please select a delivery boy", Toast.LENGTH_SHORT).show();
                    } else {
                        String selectedBoy = deliveryBoys[pos];

                        // 🔥 YAHAN ORDER KO ACCEPT + ONGOING STATUS PE LE JAO (Future: Firebase)
                        // Example: Toast dikhate hain abhi:
                        Toast.makeText(this,
                                "Order accepted and assigned to " + selectedBoy,
                                Toast.LENGTH_LONG).show();

                        // future me yahan status = "ongoing" update karo, then:
                        finish();
                    }
                })
                .show();
    }

}