package com.example.wamp.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wamp.model.Match
import com.example.wamp.model.MatchesResponse
import com.example.wamp.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MatchesPageViewModel : ViewModel() {

    val currentMatches: MutableLiveData<MutableList<Match>> by lazy {
        MutableLiveData<MutableList<Match>>()
    }

    val currentError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getMatches(token: String) {
        try {
            ApiClient().getApiService().getMatches(token = "Bearer $token")
                .enqueue(object : Callback<MatchesResponse> {
                    override fun onFailure(call: Call<MatchesResponse>, t: Throwable) {
                        // Error fetching matches
                        currentError.value = "Error occurred!"
                    }

                    override fun onResponse(call: Call<MatchesResponse>, response: Response<MatchesResponse>) {
                        if (response.code() == 200) {
                            currentMatches.setValue(response.body()!!.data)
                        } else {
                            currentError.value = "Error loading your matches :("
                        }
                    }
                })
        } catch (e: Exception) {
            // Exception
            currentError.value = "Exception occurred"
            Log.d("Error occurred", e.message.toString())
        }
    }
}
