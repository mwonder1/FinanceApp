package com.example.android.financeapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StocksDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = StocksDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "stocks.db";
    private static final int DATABASE_VERSION = 1;

    public StocksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HOLDINGS_TABLE =  "CREATE TABLE " + StocksContract.StockEntry.TABLE_NAME + " ("

                + StocksContract.StockEntry.COLUMN_TICKER + " String PRIMARY KEY NOT NULL,"

                + StocksContract.StockEntry.COLUMN_SHARE + " INT NOT NULL, "

                + StocksContract.StockEntry.COLUMN_COST + " INT NOT NULL);";

        db.execSQL(SQL_CREATE_HOLDINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}