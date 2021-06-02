package com.example.bangunfas.model

import com.google.gson.annotations.SerializedName

class LoginResponse (
    @SerializedName("token") val token: String
    //serialized name = nama yang dikirimkan

)