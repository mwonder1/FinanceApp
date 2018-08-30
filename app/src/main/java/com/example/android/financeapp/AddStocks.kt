package com.example.android.financeapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button

//ToDo: Create an adapter class for the Recycler
class AddStocks : AppCompatActivity() {

    lateinit var backBtn: Button

    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_stocks)

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
        // COMPLETED (42) Use setHasFixedSize(true) on mRecyclerView to designate that all items in the list will have the same size
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true)

        // COMPLETED (43) set mForecastAdapter equal to a new ForecastAdapter
        /*
         * The ForecastAdapter is responsible for linking our weather data with the Views that
         * will end up displaying our weather data.
         */
        //mForecastAdapter = ForecastAdapter()

        // COMPLETED (44) Use mRecyclerView.setAdapter and pass in mForecastAdapter
        /* Setting the adapter attaches it to the RecyclerView in our layout. */

       // mRecyclerView.setAdapter(mForecastAdapter)


    }
}
