package com.example.wamp.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("api_token")
    var authToken: String
)