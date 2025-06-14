package com.example.assignment_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ElectricityBills.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "bills";
    private static final String COL_ID = "id";
    private static final String COL_MONTH = "month";
    private static final String COL_UNITS = "units";
    private static final String COL_REBATE = "rebate";
    private static final String COL_TOTAL = "total";
    private static final String COL_FINAL = "final";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MONTH + " TEXT, " +
                COL_UNITS + " INTEGER, " +
                COL_REBATE + " INTEGER, " +
                COL_TOTAL + " REAL, " +
                COL_FINAL + " REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertBill(ElectricityBill bill) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MONTH, bill.month);
        values.put(COL_UNITS, bill.units);
        values.put(COL_REBATE, bill.rebate);
        values.put(COL_TOTAL, bill.totalCharges);
        values.put(COL_FINAL, bill.finalCost);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<ElectricityBill> getAllBills() {
        ArrayList<ElectricityBill> bills = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                ElectricityBill bill = new ElectricityBill(
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5)
                );
                bills.add(bill);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bills;
    }
}
