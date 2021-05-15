package com.example.wamp.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wamp.model.LoginRequest
import com.example.wamp.model.LoginResponse
import com.example.wamp.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginPageViewModel : ViewModel() {

    val currentAuthToken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun userLogin(email: String, password: String) {
        try {
            ApiClient().getApiService().login(
                LoginRequest(
                    email,
                    password
                )
            )
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        // Error logging in
                        currentError.value = "Error occurred!"
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.code() == 200) {
                            // Save the token
                            currentAuthToken.setValue(response.body()!!.authToken)
                        } else {
                            // Error logging in
                            currentError.setValue("Incorrect username or password!")
                        }
                    }

                })
        } catch (e: Exception) {
            // Exception
            currentError.value = "Exception occurred"
        }
    }
}