package com.example.wamp.api

import com.example.wamp.model.LoginRequest
import com.example.wamp.model.LoginResponse
import com.example.wamp.model.MatchesResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("matches")
    fun getMatches(@Header("Authorization") token: String): Call<MatchesResponse>
}