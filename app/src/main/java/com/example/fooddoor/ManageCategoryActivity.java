package com.example.fooddoor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.AdminCategoryAdapter;
import com.example.fooddoor.Modelclass.AdminCategoryModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ManageCategoryActivity extends AppCompatActivity {

    private List<AdminCategoryModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        Toolbar toolbar = findViewById(R.id.toolbar_manage_category);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.rv_categories);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        list.add(new AdminCategoryModel("1", "Snacks", ""));
        list.add(new AdminCategoryModel("2", "Chinese", ""));
        list.add(new AdminCategoryModel("3", "Desserts", ""));

        rv.setAdapter(new AdminCategoryAdapter(this, list));

        MaterialButton add = findViewById(R.id.btn_add_category);
        add.setOnClickListener(v ->
                startActivity(new Intent(this, AdminAddCategoryActivity.class))
        );
    }
}
