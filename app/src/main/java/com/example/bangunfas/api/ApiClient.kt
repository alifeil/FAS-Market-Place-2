package com.example.bangunfas.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
   public var BASE_URL: String = "http://fia.teknisitik.com/api/v1/"
   public var retrofit: Retrofit? = null

   public fun getApiClient(): Retrofit?{
      if(retrofit == null){
           retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create()).build()
      }
      return retrofit
    }
}

