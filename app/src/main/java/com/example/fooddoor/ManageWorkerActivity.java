package com.example.fooddoor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddoor.Adapter.WorkerAdapter;
import com.example.fooddoor.Modelclass.WorkerModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManageWorkerActivity extends AppCompatActivity {

    RecyclerView recyclerWorkers;
    WorkerAdapter adapterRecycler;
    ArrayList<WorkerModel> workerList = new ArrayList<>();
    FloatingActionButton fabAddWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker);

        recyclerWorkers = findViewById(R.id.recyclerWorkers);
        fabAddWorker = findViewById(R.id.fabAddWorker);

        // Dummy workers
        workerList.add(new WorkerModel("Ramesh", "ramesh@email.com", "1234", "Delivery Boy"));
        workerList.add(new WorkerModel("Suresh", "suresh@email.com", "1234", "Admin"));

        adapterRecycler = new WorkerAdapter(workerList, this);
        recyclerWorkers.setLayoutManager(new LinearLayoutManager(this));
        recyclerWorkers.setAdapter(adapterRecycler);

        fabAddWorker.setOnClickListener(v -> openAddWorkerDialog());
    }

    private void openAddWorkerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_worker, null);
        builder.setView(dialogView);

        EditText edtName = dialogView.findViewById(R.id.edit_text_name);
        EditText edtEmail = dialogView.findViewById(R.id.edit_text_email);
        EditText edtPassword = dialogView.findViewById(R.id.edit_text_password);
        AutoCompleteTextView roleSpinner = dialogView.findViewById(R.id.spinner_worker_role);

        // Spinner setup
        String[] roles = {"Delivery Boy", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.dropdown_item, roles);
        roleSpinner.setAdapter(adapter);
        roleSpinner.setFocusable(false);
        roleSpinner.setClickable(true);

        MaterialButton btnAdd = dialogView.findViewById(R.id.btn_dialog_add);
        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String role = roleSpinner.getText().toString().trim();

            if (!name.isEmpty() && !role.isEmpty()) {
                workerList.add(new WorkerModel(name, email, password, role));
                adapterRecycler.notifyItemInserted(workerList.size() - 1);
            } else {
                Toast.makeText(this, "Enter valid data", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }
}