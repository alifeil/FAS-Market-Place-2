package com.example.bangunfas.api

import com.example.bangunfas.model.CategoryModel
import com.example.bangunfas.model.DefaultResponse
import com.example.bangunfas.model.LoginResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<LoginResponse> //kalo callback berhasil ini yang dikeluarkan, jadi membuat model

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<DefaultResponse> //pesan response yang dipostman jadi membuat model

    @GET("categories")
    fun getCategories(): Call<JsonObject>
//    fun getCategories(@Header("Authorization") authHeader:String)
//    : Call<JsonObject> //membutuhkan bearer token


    @GET("products/searchByCategory/{categoryId}")
    fun getProductsByCategory(@Header("Authorization")authHeader: String,
                                @Path("categoryId")id:Int):Call<JsonObject>

}