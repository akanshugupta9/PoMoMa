package com.example.akanshugupta9.pomoma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static android.R.attr.format;
import static android.R.attr.name;

/**
 * Created by akanshugupta9 on 17/1/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PoMoMa.db";
    public static final String TRANSACTION_TABLE_NAME = "transactions";
    public static final String TRANSACTION_COLUMN_DATE = "date";
    public static final String TRANSACTION_COLUMN_SUMMARY = "summary";
    public static final String TRANSACTION_COLUMN_TYPE = "type";
    public static final String TRANSACTION_COLUMN_AMOUNT = "amount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table transactions ( amount float, type integer, summary varchar(140), date varchar(50) )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS transactions");
        onCreate(db);
    }

    public boolean insertTransaction (Float amount, Integer type, String summary, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("type", type);
        contentValues.put("summary", summary);
        contentValues.put("date", date);
        db.insert("transactions", null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TRANSACTION_TABLE_NAME);
        return numRows;
    }

    public TransactionData getData() throws ParseException {
        TransactionData td = new TransactionData();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from transactions", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            td.amount.add(res.getFloat(res.getColumnIndex(TRANSACTION_COLUMN_AMOUNT)));
            td.summary.add(res.getString(res.getColumnIndex(TRANSACTION_COLUMN_SUMMARY)));
            td.date.add(res.getString(res.getColumnIndex(TRANSACTION_COLUMN_DATE)));
            td.type.add(res.getInt(res.getColumnIndex(TRANSACTION_COLUMN_TYPE)));
            res.moveToNext();
        }
        return td;
    }
}