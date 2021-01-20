package com.example.wamp

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("auth/login")
    //@FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("matches")
    fun getMatches(@Header("Authorization") token: String): Call<MatchesResponse>
}