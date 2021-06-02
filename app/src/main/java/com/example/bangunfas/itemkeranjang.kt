package com.example.bangunfas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class itemkeranjang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemkeranjang)
        supportActionBar!!.setTitle("My Shopping")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}