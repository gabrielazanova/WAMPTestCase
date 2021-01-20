package com.example.wamp

import com.google.gson.annotations.SerializedName

data class Match (

    @SerializedName("id")
    var id: Int,

    @SerializedName("is_cup_match")
    var is_cup_match: Boolean,

    @SerializedName("users")
    var users: List<User>,

    @SerializedName("course")
    var course: Course

    /*@SerializedName("images")
    var image: MatchImage*/
)