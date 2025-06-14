package com.example.assignment_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMonth;
    EditText editTextUnits;
    SeekBar seekBarRebate;
    TextView textViewRebateValue, textViewTotalCharges, textViewFinalCost;
    Button buttonCalculate, buttonViewList, buttonAbout;

    int rebatePercent = 0;
    DatabaseHelper dbHelper;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        // UI elements
        spinnerMonth = findViewById(R.id.spinnerMonth);
        editTextUnits = findViewById(R.id.editTextUnits);
        seekBarRebate = findViewById(R.id.seekBarRebate);
        textViewRebateValue = findViewById(R.id.textViewRebateValue);
        textViewTotalCharges = findViewById(R.id.textViewTotalCharges);
        textViewFinalCost = findViewById(R.id.textViewFinalCost);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonViewList = findViewById(R.id.buttonViewList);
        buttonAbout = findViewById(R.id.buttonAbout);

        dbHelper = new DatabaseHelper(this);

        // Spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.month_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);

        // SeekBar
        seekBarRebate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rebatePercent = progress;
                textViewRebateValue.setText("Rebate: " + rebatePercent + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Calculate and Save
        buttonCalculate.setOnClickListener(view -> {
            String month = spinnerMonth.getSelectedItem().toString();
            String unitStr = editTextUnits.getText().toString().trim();

            if (unitStr.isEmpty()) {
                editTextUnits.setError("Please enter electricity usage");
                return;
            }

            double units;
            try {
                units = Double.parseDouble(unitStr);
                if (units < 0) {
                    editTextUnits.setError("Value cannot be negative");
                    return;
                }
            } catch (NumberFormatException e) {
                editTextUnits.setError("Invalid number format");
                return;
            }

            double totalCharges = calculateCharges(units);
            double finalCost = totalCharges - (totalCharges * rebatePercent / 100.0);

            textViewTotalCharges.setText(String.format("Total Charges: RM %.2f", totalCharges));
            textViewFinalCost.setText(String.format("Final Cost after Rebate: RM %.2f", finalCost));

            ElectricityBill bill = new ElectricityBill(month, (int) units, rebatePercent, totalCharges, finalCost);
            dbHelper.insertBill(bill);

            Toast.makeText(this, "Saved locally", Toast.LENGTH_SHORT).show();
        });

        // View saved bills
        buttonViewList.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BillListActivity.class)));

        // About page
        buttonAbout.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));
    }

    private double calculateCharges(double units) {
        double total;
        if (units <= 200) {
            total = units * 0.218;
        } else if (units <= 300) {
            total = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            total = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else {
            total = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (units - 600) * 0.546;
        }
        return total;
    }
    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> finishAffinity())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

}
