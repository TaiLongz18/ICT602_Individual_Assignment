package com.example.assignment_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BillDetailActivity extends AppCompatActivity {

    TextView textMonth, textUnits, textRebate, textTotal, textFinal;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        // Bind views
        textMonth = findViewById(R.id.textMonth);
        textUnits = findViewById(R.id.textUnits);
        textRebate = findViewById(R.id.textRebate);
        textTotal = findViewById(R.id.textTotal);
        textFinal = findViewById(R.id.textFinal);

        // Get passed data
        String month = getIntent().getStringExtra("month");
        int units = getIntent().getIntExtra("units", 0);
        int rebate = getIntent().getIntExtra("rebate", 0);
        double total = getIntent().getDoubleExtra("total", 0);
        double finalCost = getIntent().getDoubleExtra("final", 0);

        // Set data to UI
        textMonth.setText("Month: " + month);
        textUnits.setText("Units Used: " + units + " kWh");
        textRebate.setText("Rebate: " + rebate + "%");
        textTotal.setText(String.format("Total Charges: RM %.2f", total));
        textFinal.setText(String.format("Final Cost: RM %.2f", finalCost));
    }
}
