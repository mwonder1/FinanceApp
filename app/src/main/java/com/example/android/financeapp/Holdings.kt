package com.example.android.financeapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.financeapp.data.CustomAdapter
import com.example.android.financeapp.data.StocksContract
import com.example.android.financeapp.data.StocksDbHelper
import kotlinx.android.synthetic.main.holdings.*

class Holdings : AppCompatActivity() {

    lateinit var mDatabase: SQLiteDatabase
    //lateinit var mAdapter: CustomAdapter
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDbHelper: StocksDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.holdings)

        mDbHelper = StocksDbHelper(this)
        mDatabase = mDbHelper.writableDatabase

        mRecyclerView = findViewById(R.id.recylcerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = layoutManager

        mRecyclerView.setHasFixedSize(true)

        val mAdapter = CustomAdapter.CustomViewHolder(applicationContext, getAllItems())
        recylcerView.adapter = mAdapter

       // displayDbInfo()
    }

    override fun onStart() {
        super.onStart()
    }

//    fun displayDbInfo(){
//        val mDbHelper = StocksDbHelper(this)
//        val db = mDbHelper.readableDatabase
//
//        val cursor: Cursor = db.rawQuery("SELECT * FROM ${StocksContract.StockEntry.TABLE_NAME} WHERE ticker = NVDA", null)
//
//        cursor.close()
//    }

    fun getAllItems() : Cursor{
        val query = mDatabase.query(
                StocksContract.StockEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                StocksContract.StockEntry.COLUMN_COST + " DESC"
        );
        return query

    }

    fun btn(v: View){

        if (v.id == R.id.fab )
            startActivity(Intent(applicationContext, AddStocks::class.java))
    }
}