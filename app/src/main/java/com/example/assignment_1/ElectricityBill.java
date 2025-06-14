package com.example.assignment_1;

public class ElectricityBill {
    public String month;
    public int units;
    public int rebate;
    public double totalCharges;
    public double finalCost;

    public ElectricityBill() {
        // Required default constructor
    }

    public ElectricityBill(String month, int units, int rebate, double totalCharges, double finalCost) {
        this.month = month;
        this.units = units;
        this.rebate = rebate;
        this.totalCharges = totalCharges;
        this.finalCost = finalCost;
    }
}
