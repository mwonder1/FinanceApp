package com.example.android.financeapp

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android.financeapp.data.CustomAdapter
import com.example.android.financeapp.data.StocksContract
import com.example.android.financeapp.data.StocksDbHelper

//ToDo: Create an adapter class for the Recycler
class AddStocks : AppCompatActivity() {

    lateinit var backBtn: Button
    lateinit var navMenu: Menu
    lateinit var deleteBtn: MenuItem
    lateinit var tvTotalHoldings: TextView
    lateinit var tvTicker: TextView
    lateinit var tvShares: TextView
    lateinit var tvCost: TextView
    lateinit var mDbHelper: StocksDbHelper
    lateinit var mDatabase: SQLiteDatabase
    lateinit var mAdapter: CustomAdapter
    lateinit var toolbar: ActionBar
    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_stocks)
        Toast.makeText(this@AddStocks, "Please enter your current assets.", Toast.LENGTH_SHORT).show()

        tvTotalHoldings = findViewById(R.id.tv_TotalHoldings)
        tvTicker = findViewById(R.id.tv_ticker)
        tvShares = findViewById(R.id.tv_shares)
        tvCost = findViewById(R.id.tv_cost)

        mDbHelper = StocksDbHelper(this)
        mDatabase = mDbHelper.writableDatabase

        cursor = getAllItems()
        mAdapter = CustomAdapter(applicationContext, cursor)


        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.navigation_holdings-> startActivity(Intent(applicationContext, Holdings::class.java))
            R.id.navigation_add_stocks -> startActivity(Intent(applicationContext, AddStocks::class.java))
        }
        false
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

    private fun getAllItems() : Cursor{
        val query = mDatabase.query(
                StocksContract.StockEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                StocksContract.StockEntry.COLUMN_COST + " DESC"
        )
        return query
    }

    private fun insertDbData(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.writableDatabase

        if(checkValues()) {

            val values = ContentValues()
            values.put(StocksContract.StockEntry.COLUMN_TICKER, tvTicker.text.toString())
            values.put(StocksContract.StockEntry.COLUMN_SHARE, tvShares.text.toString())
            values.put(StocksContract.StockEntry.COLUMN_COST, tvCost.text.toString())

            val newRowId = db.insert(StocksContract.StockEntry.TABLE_NAME, null, values).toInt()
            mAdapter.swapCursor(newCursor = getAllItems())

            if (newRowId == -1) {
                Toast.makeText(this, "Error with saving stock", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Stock saved with row id: " + newRowId, Toast.LENGTH_SHORT).show()
            }
            displayDbInfo()
        }
        else{
            Toast.makeText(this@AddStocks, "Please enter valid data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValues(): Boolean {
        return tvCost.text.toString() != "" && tvCost.text.toString() != "Cost" &&
                tvShares.text.toString() != "" && tvShares.text.toString() != "Shares" &&
                 tvTicker.text.toString() != "" && tvTicker.text.toString() != "Ticker"
    }

    private fun deleteDb(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.writableDatabase

        db.delete(StocksContract.StockEntry.TABLE_NAME, null, null)
        displayDbInfo()
    }

    fun onClick(tv: View){
        val id = tv.id
        when(id){
            R.id.tv_ticker -> tvTicker.text = ""
            R.id.tv_shares -> tvShares.text = ""
            R.id.tv_cost -> tvCost.text = ""
        }
    }

    fun displayDbInfo(){
        val mDbHelper = StocksDbHelper(this)
        val db = mDbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM ${StocksContract.StockEntry.TABLE_NAME}", null)
        tvTotalHoldings.text = "Number of stocks: ${cursor.count}"
        cursor.close()
    }

    fun submitBtn(v: View){
        if (v.id == R.id.submitBtn){
            insertDbData()
        }
    }
}
