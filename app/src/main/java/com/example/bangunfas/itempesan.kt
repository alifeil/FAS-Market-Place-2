package com.example.bangunfas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class itempesan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itempesan)

        supportActionBar!!.setTitle("My Messages")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}