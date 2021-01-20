package com.example.wamp

import com.google.gson.annotations.SerializedName

data class Course (

    @SerializedName("name")
    var name: String,

    @SerializedName("country")
    var country: String,

    @SerializedName("city")
    var city: String
)