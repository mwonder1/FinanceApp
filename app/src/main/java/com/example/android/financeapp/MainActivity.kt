package com.example.android.financeapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var settings: MenuItem
    private lateinit var addStocks: MenuItem
    private lateinit var navMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_nav, menu)
        navMenu = menu
        addStocks = menu.findItem(R.id.addStocks)
        settings = menu.findItem(R.id.settings)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        when (id){
            R.id.addStocks -> startActivity(Intent(applicationContext, AddStocks::class.java))
            R.id.settings -> Toast.makeText(this@MainActivity, "Currently, there are no settings to change. Sorry!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}
