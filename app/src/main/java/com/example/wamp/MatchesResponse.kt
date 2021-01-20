package com.example.wamp

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class MatchesResponse (

    /*@SerializedName("data")
    var data: List<Match>*/

    @SerializedName("data")
    var data: List<JSONObject>
)