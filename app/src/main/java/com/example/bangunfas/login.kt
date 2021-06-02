package com.example.bangunfas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bangunfas.api.ApiClient
import com.example.bangunfas.api.ApiInterface
import com.example.bangunfas.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var  sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.setTitle("Login")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)//tandapanah
            loginbtn.setOnClickListener {
                goToSignUpPage()
            }
//        val etEmail = findViewById<EditText>(R.id.emailtxt)
//        val etPassword = findViewById<EditText>(R.id.passtxt)
//        val btnLogin = findViewById<Button>(R.id.loginbtn)
//
//        btnLogin.setOnClickListener(){
//            if (etEmail.text.toString()=="") {
//                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
//                etEmail.setSelection(0)
//                true
//            }else if (etPassword.text.toString()==""){
//                Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
//                etPassword.setSelection(0)
//                true
//            }else
//            {
//             false
//            }
//        }
    loginmasuk()
    }
    fun loginmasuk()
    {
        val email = emailtxt.text.toString()
        val password = passtxt.text.toString()

        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Isikan Email atau Password ",Toast.LENGTH_SHORT).show()
        }else{
            var apiInterface : ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
            var requestCall : Call<LoginResponse> = apiInterface.login(email, password)
            requestCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.isSuccessful){
                        Log.d("log",response.body()?.token.toString())
                        val token: String = response.body()?.token.toString()
                        //untuk menyimpan token ke dalam sharedpreferences
                        sharedPref = getSharedPreferences("SharePref",Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPref.edit()
                        editor.putString("token", token)
                        editor.apply()
                        Toast.makeText(this@login, "login sukses!", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this@login, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@login,"email atau password salah", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, "Gagal Masuk"+t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
    fun goToSignUpPage(){
        val intent = Intent(this,profile ::class.java )
        startActivity(intent)

    }
    override fun onSupportNavigateUp(): Boolean { //tanda panah balik
        onBackPressed()
        return true
    }
}