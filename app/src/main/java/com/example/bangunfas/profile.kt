package com.example.bangunfas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bangunfas.api.ApiClient
import com.example.bangunfas.api.ApiInterface
import com.example.bangunfas.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.net.Inet4Address

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar!!.setTitle("Register")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)// tanda panah

        btnSignup.setOnClickListener {
            register()
        }
//
//        val etFirstName = findViewById<EditText>(R.id.tvName)
////        val etLastName = findViewById<EditText>(R.id.lastname) //filelama
//        val etEmail = findViewById<EditText>(R.id.tvEmail)
//        val etPassword = findViewById<EditText>(R.id.tvPassword)
//        val etRepeatPassword = findViewById<EditText>(R.id.password2)
//        val btnRegister = findViewById<Button>(R.id.btnSignup)
//
//        btnRegister.setOnClickListener {
//            if (etFirstName.text.toString() == "") {
//                Toast.makeText(this, "First name tidak boleh kosong!", Toast.LENGTH_SHORT).show()
//                etFirstName.setSelection(0)
//                true
//           //else if (etLastName.text.toString() == "") {   //filelama
//               //Toast.makeText(                            //filelama
//                //  this, "Last name tidak boleh kosong",   //filelama
//                  // Toast.LENGTH_SHORT                     //filelama
//              // ).show()                                   //filelama
//               // etLastName.setSelection(0)                //filelama
//                //true                                      //filelama
//            } else if (etEmail.text.toString() == "") {
//                Toast.makeText(
//                    this, "Email tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                ).show()
//                etEmail.setSelection(0)
//                true
//            } else if (etPassword.text.toString() == "") {
//                Toast.makeText(
//                    this, "Password tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                ).show()
//                true
//            } else if (etRepeatPassword.text.toString() == "") {
//                Toast.makeText(
//                    this, "Repeat password tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                ).show()
//                true
//            } else if (etPassword.text.toString() !=
//                etRepeatPassword.text.toString()
//            ) {
//                Toast.makeText(
//                    this, "Repeat password harus sama dengan password",
//                    Toast.LENGTH_SHORT
//                ).show()
//                true
//            } else {
//                val get_f_name = etFirstName.text.toString()
////                val get_l_name = etLastName.text.toString()       //filelama
//                val getemail = etEmail.text.toString()
//                val getpassword = etPassword.text.toString()
//                val getrepeatpassword = etRepeatPassword.text.toString()
//
//                val intent = Intent(applicationContext, show_profile::class.java)
//                intent.putExtra("F_Name", get_f_name)
////                intent.putExtra("L_Name", get_l_name)            //filelama
//                intent.putExtra("Email", getemail)
//                intent.putExtra("Password", getpassword)
//                intent.putExtra("R_password", getrepeatpassword)
//                startActivity(intent)
//
//            }
//        }
//    }
    }
    fun register(){
        var name = tvName.text.toString()
        var email = tvEmail.text.toString()
        var password = tvPassword.text.toString()
        var password2 = password2.text.toString()
        if(password != password2){
            return Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
        }
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            return Toast.makeText(this, "Masih ada field yang kosong", Toast.LENGTH_SHORT).show()
        }
        var apiInterface : ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface :: class.java)
        var requestCall: Call<DefaultResponse> = apiInterface.register(name, email, password)
        requestCall.enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                if(response.isSuccessful){
                    Toast.makeText(this@profile, "Proses Register Berhasil", Toast.LENGTH_SHORT).show()
                    Log.d("log", response.body()?.success.toString())
                    val intent = Intent(this@profile, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@profile, "Proses registrasi gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
             Toast.makeText(baseContext, "Proses registrasi gagal"+t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
    override fun onSupportNavigateUp(): Boolean { //tanda panah balik
        onBackPressed()
        return true
    }
}
