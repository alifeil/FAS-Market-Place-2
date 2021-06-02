package com.example.bangunfas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.provider.ContactsContract
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangunfas.adapter.CategoryAdapter
import com.example.bangunfas.api.ApiClient
import com.example.bangunfas.api.ApiInterface
import com.example.bangunfas.model.CategoryModel
import com.example.navigasiapp.adapter.product
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navDrawerView: NavigationView
    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var  sharedPref : SharedPreferences
    var token : String = ""
    var myAdapter : CategoryAdapter? = null

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentcontainer, fragment)
            commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val SDK_INT = Build.VERSION.SDK_INT
        if(SDK_INT > 8){
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            sharedPref = getSharedPreferences("SharePref", Context.MODE_PRIVATE)
            token = sharedPref.getString("token", "")!!
            var apiInterface : ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
            var requestCall: Call<JsonObject> = apiInterface.getCategories()
            requestCall.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val myJson = response.body()

                    Log.d("my log", myJson.toString())
                    val myData = myJson!!.getAsJsonArray("data")

                    myAdapter = CategoryAdapter(this@MainActivity)

                    val arrayItem = ArrayList<CategoryModel>()
                    for(i in 0 until myData.size()){
                        var myRecord : JsonObject = myData.get(i).asJsonObject
                        var id = myRecord.get("id").asInt
                        var name = myRecord.get("name").asString
                        //var image_link = myRecord.get("image_link").asString //codingan untuk image
                        Log.d("log "+i.toString(), myData.get(i).toString())
                       // arrayItem.add(CategoryModel(id, name, image_link)) //codingan untuk image
                        arrayItem.add(CategoryModel(id, name))
                    }
                    Log.d("Array Item", arrayItem.toString())
                    myAdapter!!.setData(arrayItem)

                    product_recycleview.layoutManager = LinearLayoutManager(this@MainActivity)
                    product_recycleview.adapter = myAdapter
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.d("gagal", t.toString())
                }
            })

        }

        //call fragment from bottom navigation view
        val firstFragment = home()
        val secondFragment = feed()
        val thirdFragment = store()

        setCurrentFragment(firstFragment)
        bottomNavigation = findViewById(R.id.navbottom)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> setCurrentFragment(firstFragment)
                R.id.navigation_feed -> setCurrentFragment(secondFragment)
                R.id.navigation_store -> setCurrentFragment(thirdFragment)
            }
            true
        }

        drawerLayout = findViewById(R.id.activity_main)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        //call findViewbyId on the navigation view
        navDrawerView = findViewById(R.id.navdraw)
        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myprofile -> {
                    val intent = Intent(this, profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.login -> {
                    val intent = Intent(this, login::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

//    untuk apabila tidak login tidak akan mendapatkan token dan akan kembali ke halaman semula
  //  override fun onStart() {
       // super.onStart() //dijalankan apabila register ada di main activity
//        sharedPref = getSharedPreferences("SharePref", Context.MODE_PRIVATE) //sharedpref untuk mendapatkan token
//        token = sharedPref.getString("token", "")!!
//
//        if (token==""){
//            Toast.makeText(this,"token"+token, Toast.LENGTH_SHORT).show()
//            val intent = Intent(this,login::class.java )
//            startActivity(intent)
//        }
   // }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.pesanku) {
            val intent = Intent(this, itempesan::class.java)
            startActivity(intent)
            return true
        } else if (id == R.id.notifikasiku) {
            val intent = Intent(this, itemnotifikasi::class.java)
            startActivity(intent)
            return true
        } else if (id == R.id.keranjangku) {
            val intent = Intent(this, itemkeranjang::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}