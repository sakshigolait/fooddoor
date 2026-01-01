package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.AdminItemAdapter;
import com.example.fooddoor.Modelclass.AdminMenuItem;

import java.util.ArrayList;
import java.util.List;

public class ManageItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdminItemAdapter adapter;
    private Toolbar toolbar;
    private List<AdminMenuItem> itemList;
    private ActivityResultLauncher<Intent> itemDetailLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items);

        toolbar = findViewById(R.id.toolbar_admin_items);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());


        Button addMenuItems = findViewById(R.id.btn_admin_add_menu_item);
        recyclerView = findViewById(R.id.recycler_view_admin_items);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ✅ INIT LIST ONCE
        itemList = new ArrayList<>();
        loadItems();

        // ✅ REGISTER LAUNCHER (MOST IMPORTANT)
        itemDetailLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                String deletedId =
                                        result.getData().getStringExtra("DELETED_ITEM_ID");
                                if (deletedId != null) {
                                    adapter.removeItemById(deletedId);
                                }
                            }
                        }
                );

        // ✅ SET ADAPTER WITH LAUNCHER
        adapter = new AdminItemAdapter(this, itemList, itemDetailLauncher);
        recyclerView.setAdapter(adapter);

        // Add new menu item
        addMenuItems.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminItemDetailActivity.class);
            itemDetailLauncher.launch(intent);
        });
    }

    private void loadItems() {
        // 🔥 MOCK DATA (real app me API/DB se aayega)
        itemList.clear();
        itemList.add(new AdminMenuItem(
                "001",
                "Gourmet Burger",
                "Mains",
                14.99,
                "https://example.com/burger.jpg",
                "Best selling burger with secret sauce."
        ));
        itemList.add(new AdminMenuItem(
                "002",
                "Avocado Toast",
                "Breakfast",
                8.50,
                "https://example.com/toast.jpg",
                "Healthy start to your day."
        ));
    }
}
