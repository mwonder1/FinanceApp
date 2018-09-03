////ToDo: Get Database class functioning and get an input from the user's text edit when they press 'Submit'
//package com.example.android.financeapp.data
//
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import com.example.android.financeapp.data.StocksContract
//
//class StockDbHelper(context: Context) : SQLiteOpenHelper(context, "example.db", null, 1) {
//
//     val DATABASE_VERSION = 1
//     val DATABASE_NAME = "stocks.db"
//
////    fun DatabaseHelper(context: Context){
////        super(context, DATABASE_NAME, null, DATABASE_VERSION)
////    }
//
//
//        override fun onCreate(db: SQLiteDatabase){
//
//        val SQL_CREATE_ENTRIES =
//                "CREATE TABLE ${StocksContract.StockEntry.TABLE_NAME} (" +
//                        "${StocksContract.StockEntry.} TEXT PRIMARY KEY," +
//                        "${StocksContract.StockEntry.} INTEGER," +
//                        "${COL3} INTEGER)"
//
//        db.execSQL(SQL_CREATE_ENTRIES)
//    }
//
//    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, int: Int, int2: Int){
//
//    }
//
//                        }
//
//

