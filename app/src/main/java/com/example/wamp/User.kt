package com.example.wamp

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("handicap")
    var handicap: Int
)