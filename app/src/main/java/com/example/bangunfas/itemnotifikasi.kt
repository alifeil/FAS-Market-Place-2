package com.example.bangunfas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class itemnotifikasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemnotifikasi)
        supportActionBar!!.setTitle("My Notification")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}