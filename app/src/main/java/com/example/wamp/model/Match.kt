package com.example.wamp.model

import com.google.gson.annotations.SerializedName

data class Match (

    @SerializedName("id")
    var id: Int,

    @SerializedName("played_at")
    var played_at: String,

    @SerializedName("like_count")
    var like_count: Int,

    @SerializedName("comment_count")
    var comment_count: Int

)