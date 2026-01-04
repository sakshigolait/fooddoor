package com.example.fooddoor;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.AdminOngoingOrderItemAdapter;
import com.example.fooddoor.Modelclass.AdminOngoingOrderModel;
import com.example.fooddoor.R;

import java.text.DecimalFormat;

public class AdminOngoingOrderDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ADMIN_ONGOING_ORDER = "extra_admin_ongoing_order";

    private AdminOngoingOrderModel ongoingOrder;
    private DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ongoing_order_details);

        // 1) Model receive
        ongoingOrder = (AdminOngoingOrderModel) getIntent()
                .getSerializableExtra(EXTRA_ADMIN_ONGOING_ORDER);

        if (ongoingOrder == null) {
            finish();
            return;
        }

        // 2) Views find
        ImageView btnBack = findViewById(R.id.adminOngoingDetail_buttonBack);
        ImageView imgAvatar = findViewById(R.id.adminOngoingDetail_imageAvatar);

        TextView tvCustomer = findViewById(R.id.adminOngoingDetail_textCustomerName);
        TextView tvTime = findViewById(R.id.adminOngoingDetail_textTime);
        TextView tvOrderId = findViewById(R.id.adminOngoingDetail_textOrderId);
        TextView tvPhone = findViewById(R.id.adminOngoingDetail_textPhone);
        TextView tvEmail = findViewById(R.id.adminOngoingDetail_textEmail);
        TextView tvAddress = findViewById(R.id.adminOngoingDetail_textAddress);
        TextView tvMessage = findViewById(R.id.adminOngoingDetail_textMessage);

        TextView tvSubtotal = findViewById(R.id.adminOngoingDetail_textSubtotal);
        TextView tvDelivery = findViewById(R.id.adminOngoingDetail_textDeliveryFee);
        TextView tvTax = findViewById(R.id.adminOngoingDetail_textServiceTax);
        TextView tvDiscount = findViewById(R.id.adminOngoingDetail_textDiscount);
        TextView tvTotal = findViewById(R.id.adminOngoingDetail_textTotal);

        RecyclerView rvItems = findViewById(R.id.adminOngoingDetail_itemsRecyclerView);

        Button btnCall = findViewById(R.id.adminOngoingDetail_buttonCall);
        Button btnEmail = findViewById(R.id.adminOngoingDetail_buttonEmail);


        Button btnCancel = findViewById(R.id.adminOngoingDetail_buttonCancelOrder);

        Spinner spinnerStatus = findViewById(R.id.adminOngoingDetail_spinnerStatus);

        // 3) Data set in views
        tvCustomer.setText(ongoingOrder.adminCustomerName);
        tvTime.setText(ongoingOrder.adminTimeText);
        tvOrderId.setText("Order id: " + ongoingOrder.adminOrderId);

        // Agar abhi model me phone/email/address/message nahi hain to placeholder
        tvPhone.setText("Not available");
        tvEmail.setText("Not available");
        tvAddress.setText("Not available");
        tvMessage.setText("");

        // Items list
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new AdminOngoingOrderItemAdapter(ongoingOrder.adminItems));

        double subtotal = ongoingOrder.getAdminTotal();
        double delivery = 0;
        double tax = 0;
        double discount = 0;
        double total = subtotal + delivery + tax - discount;

        tvSubtotal.setText("Subtotal: $" + df.format(subtotal));
        tvDelivery.setText("Delivery Fee: $" + df.format(delivery));
        tvTax.setText("Service tax (0%): $" + df.format(tax));
        tvDiscount.setText("Discount (0%): $" + df.format(discount));
        tvTotal.setText("Total: $" + df.format(total));

        // Status spinner (simple example)
        String[] statuses = new String[]{
                "Order In-Process",
                "On the Way",
                "Delivered"
        };

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                statuses
        );
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(statusAdapter);

        // Back
        btnBack.setOnClickListener(v -> onBackPressed());

        // Call (abhi sirf Toast)
        btnCall.setOnClickListener(v ->
                Toast.makeText(this, "Call action yahan add karo", Toast.LENGTH_SHORT).show()
        );

        // Email
        btnEmail.setOnClickListener(v ->
                Toast.makeText(this, "Email action yahan add karo", Toast.LENGTH_SHORT).show()
        );

        // Cancel
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Order cancelled (yahan status update karna hai)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}