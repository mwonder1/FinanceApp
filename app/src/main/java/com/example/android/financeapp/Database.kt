//package com.example.android.financeapp
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//
//class Database : SQLiteOpenHelper(){
//
//    private val TAG = "DatabaseHelper"
//    private val TABLE_NAME = "stock_table"
//    private val COL1 = "ticker"
//    private val COL2 = "name"
//
//    fun DatabaseHelper(context: Context){
//        super(context, TABLE_NAME, null, 1)
//    }
//
//    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, int: Int, int2: Int){
//
//    }
//
//    override fun onCreate(sqLiteDatabase: SQLiteDatabase){
//        val createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)"
//        db.execSQL(createTable)
//
//    }
//
//    fun addData(item: String){
//        var db: SQLiteDatabase = this@Database.writableDatabase
//        var contentValues: ContentValues = ContentValues()
//        contentValues.put(COL2, item)
//
//    }
//
//
//}
