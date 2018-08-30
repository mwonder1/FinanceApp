package com.example.android.financeapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var viewDetailsItem: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewDetailsItem = findViewById(R.id.addStocks)

        viewDetailsItem.setOnClickListener{
            val intentDetails = Intent(applicationContext, AddStocks::class.java)
            startActivity(intentDetails)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_nav, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        when (id){
            R.id.addStocks -> Toast.makeText(this@MainActivity, "You have clicked View Details", Toast.LENGTH_SHORT).show()
            R.id.editPortfolio -> Toast.makeText(this@MainActivity, "You have clicked Edit Portfolio", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this@MainActivity, "You have clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}
