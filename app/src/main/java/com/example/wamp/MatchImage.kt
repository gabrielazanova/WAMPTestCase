package com.example.wamp

import com.google.gson.annotations.SerializedName

data class MatchImage (

    @SerializedName("default")
    var defaultImage: String
)