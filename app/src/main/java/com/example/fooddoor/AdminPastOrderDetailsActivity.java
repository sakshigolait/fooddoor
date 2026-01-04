package com.example.fooddoor;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.AdminPastOrderItemAdapter;
import com.example.fooddoor.Modelclass.AdminPastOrderModel;
import com.example.fooddoor.R;

import java.text.DecimalFormat;

public class AdminPastOrderDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ADMIN_PAST_ORDER = "extra_admin_past_order";

    private AdminPastOrderModel pastOrder;
    private DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_past_order_details);

        // 1) Get model from Intent
        pastOrder = (AdminPastOrderModel) getIntent().getSerializableExtra(EXTRA_ADMIN_PAST_ORDER);
        if (pastOrder == null) {
            finish();
            return;
        }

        // 2) Find views
        ImageView btnBack = findViewById(R.id.adminPastDetail_buttonBack);
        TextView tvTitle = findViewById(R.id.adminPastDetail_appBarTitle);

        TextView tvCustomer = findViewById(R.id.adminPastDetail_textCustomerName);
        TextView tvTime = findViewById(R.id.adminPastDetail_textTime);
        TextView tvOrderId = findViewById(R.id.adminPastDetail_textOrderId);
        TextView tvMessage = findViewById(R.id.adminPastDetail_textMessage);

        TextView tvSubtotal = findViewById(R.id.adminPastDetail_textSubtotal);
        TextView tvDelivery = findViewById(R.id.adminPastDetail_textDeliveryFee);
        TextView tvTax = findViewById(R.id.adminPastDetail_textServiceTax);
        TextView tvDiscount = findViewById(R.id.adminPastDetail_textDiscount);
        TextView tvTotal = findViewById(R.id.adminPastDetail_textTotal);

        TextView tvStatus = findViewById(R.id.adminPastDetail_textStatus);

        RecyclerView rvItems = findViewById(R.id.adminPastDetail_itemsRecyclerView);

        // 3) Set basic data
        tvCustomer.setText(pastOrder.adminCustomerName);
        tvTime.setText(pastOrder.adminTimeText);
        tvOrderId.setText("Order id: " + pastOrder.adminOrderId);

        // Agar tumhare model me message nahi hai to blank rehne do
        tvMessage.setText("");

        // Items list
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new AdminPastOrderItemAdapter(pastOrder.adminItems));

        double subtotal = pastOrder.getAdminTotal();
        double delivery = 0;
        double tax = 0;
        double discount = 0;
        double total = subtotal + delivery + tax - discount;

        tvSubtotal.setText("Subtotal: $" + df.format(subtotal));
        tvDelivery.setText("Delivery Fee: $" + df.format(delivery));
        tvTax.setText("Service tax (0%): $" + df.format(tax));
        tvDiscount.setText("Discount (0%): $" + df.format(discount));
        tvTotal.setText("Total: $" + df.format(total));

        // 4) Completed vs Cancelled UI
        if (pastOrder.adminIsDelivered) {
            // ✅ COMPLETED ORDER SCREEN
            tvTitle.setText("Completed Order");
            tvStatus.setText("Order Status: Order Delivered");
            tvStatus.setTextColor(getResources().getColor(R.color.admin_status_delivered));
        } else {
            // ❌ CANCELLED ORDER SCREEN
            tvTitle.setText("Cancelled Order");
            tvStatus.setText("Order Status: Order Cancelled ");
            tvStatus.setTextColor(getResources().getColor(R.color.admin_status_cancelled));
        }

        // 5) Back button
        btnBack.setOnClickListener(v -> onBackPressed());
    }
}
