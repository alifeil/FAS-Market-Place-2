package com.example.bangunfas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar!!.setTitle("Category")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean { //tanda panah balik
        onBackPressed()
        return true
    }
}