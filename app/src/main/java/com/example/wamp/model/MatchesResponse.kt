package com.example.wamp.model

import com.example.wamp.model.Match
import com.google.gson.annotations.SerializedName

class MatchesResponse(
    @SerializedName("current_page")
    var current_page: Int,

    @SerializedName("data")
    var data: MutableList<Match>,

    @SerializedName("total")
    var total: Int
)
