package com.example.assignment_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class BillListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> billSummaries = new ArrayList<>();
    ArrayList<ElectricityBill> billObjects = new ArrayList<>();
    ArrayAdapter<String> adapter;

    DatabaseHelper dbHelper;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);

        listView = findViewById(R.id.listViewBills);
        dbHelper = new DatabaseHelper(this);

        // Load data from local DB
        billObjects = dbHelper.getAllBills();
        for (ElectricityBill bill : billObjects) {
            billSummaries.add(bill.month + " — RM " + String.format("%.2f", bill.finalCost));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, billSummaries);
        listView.setAdapter(adapter);

        // Handle item click → open detail view
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ElectricityBill selected = billObjects.get(position);
            Intent intent = new Intent(BillListActivity.this, BillDetailActivity.class);
            intent.putExtra("month", selected.month);
            intent.putExtra("units", selected.units);
            intent.putExtra("rebate", selected.rebate);
            intent.putExtra("total", selected.totalCharges);
            intent.putExtra("final", selected.finalCost);
            startActivity(intent);
        });

    }
}
