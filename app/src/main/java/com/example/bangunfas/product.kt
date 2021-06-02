package com.example.navigasiapp.adapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bangunfas.R

class product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar!!.setTitle("Product")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean { //tanda panah balik
        onBackPressed()
        return true
    }
}