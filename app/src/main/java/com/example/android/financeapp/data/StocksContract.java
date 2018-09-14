package com.example.android.financeapp.data;

import android.provider.BaseColumns;

public final class StocksContract {

    private StocksContract() {}

    public static final class StockEntry implements BaseColumns {

        /** Name of database table for holdings */

        public final static String TABLE_NAME = "holdings";
        public final static String COLUMN_TICKER = "ticker";
        public final static String COLUMN_SHARE ="shares";
        public final static String COLUMN_COST = "cost";

    }
}