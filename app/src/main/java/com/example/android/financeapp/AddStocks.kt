package com.example.android.financeapp

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android.financeapp.data.StocksContract
import com.example.android.financeapp.data.StocksDbHelper

//ToDo: Create an adapter class for the Recycler
class AddStocks : AppCompatActivity() {

    lateinit var backBtn: Button
    lateinit var navMenu: Menu
    lateinit var deleteBtn: MenuItem
    lateinit var mRecyclerView: RecyclerView
    lateinit var tvTickerRes: TextView
    lateinit var tvSharesRes: TextView
    lateinit var tvCostRes: TextView
    lateinit var tvTicker: TextView
    lateinit var tvShares: TextView
    lateinit var tvCost: TextView
    lateinit var mDbHelper: StocksDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_stocks)

        Toast.makeText(this@AddStocks, "Please enter your current assets.", Toast.LENGTH_SHORT).show()

        tvTickerRes = findViewById(R.id.tv_ticker_res)
        tvSharesRes = findViewById(R.id.tv_shares_res)
        tvCostRes = findViewById(R.id.tv_cost_res)
        tvTicker = findViewById(R.id.tv_ticker)
        tvShares = findViewById(R.id.tv_shares)
        tvCost = findViewById(R.id.tv_cost)

        backBtn = findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            val intentDetails = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentDetails)
        }
        mRecyclerView = findViewById(R.id.stockView)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // COMPLETED (41) Set the layoutManager on mRecyclerView
        mRecyclerView.layoutManager = layoutManager

        //ToDo: Hookup the Recycler to the adapter
        mRecyclerView.setHasFixedSize(true)

        //mForecastAdapter = ForecastAdapter()
       // mRecyclerView.setAdapter(mForecastAdapter)

        mDbHelper = StocksDbHelper(this)
    }

    override fun onStart(){
        super.onStart()
        displayDbInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_stocks_nav, menu)
        navMenu = menu
        deleteBtn = menu.findItem(R.id.deleteEntries)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id){
            R.id.deleteEntries -> deleteDb()
        }
        return super.onOptionsItemSelected(item)
    }

    fun insertDbData(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.writableDatabase


        val values = ContentValues()
        values.put(StocksContract.StockEntry.COLUMN_TICKER, tvTicker.text.toString())
        values.put(StocksContract.StockEntry.COLUMN_SHARE, tvShares.text.toString())
        values.put(StocksContract.StockEntry.COLUMN_COST, tvCost.text.toString())

        val newRowId = db.insert(StocksContract.StockEntry.TABLE_NAME, null, values).toInt()
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show()
        }
        displayDbInfo()
    }

    fun deleteDb(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.writableDatabase

        db.delete(StocksContract.StockEntry.TABLE_NAME, null, null)
        displayDbInfo()
    }

    fun displayDbInfo(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM ${StocksContract.StockEntry.TABLE_NAME}", null)
        tvTickerRes.text = "Number of stocks: ${cursor.count}"
        cursor.close()
    }

    fun submitBtn(v: View){
        if (v.id == R.id.submitBtn){
            insertDbData()
        }
    }
}
