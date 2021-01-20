package com.example.wamp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_first_page.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FirstPage : AppCompatActivity() {

    private lateinit var  sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        setSupportActionBar(toolbar)

        apiClient = ApiClient()
        sessionManager = SessionManager(this@FirstPage)

        try {
            // Pass the token as parameter
            apiClient.getApiService().getMatches(token = "Bearer ${sessionManager.fetchAuthToken()}")
                .enqueue(object : Callback<MatchesResponse> {
                    override fun onFailure(call: Call<MatchesResponse>, t: Throwable) {
                        // Error fetching matches
                        Toast.makeText(this@FirstPage, "Error occurred!", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<MatchesResponse>, response: Response<MatchesResponse>) {
                        if (response.code() == 200) {
                            val matchesResponse = response.body()
                            //val matches : List<JSONObject> = matchesResponse!!.data
                            Toast.makeText(this@FirstPage, "Matches: " + matchesResponse.toString(), Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@FirstPage, "Error loading your matches :(", Toast.LENGTH_LONG).show()
                        }
                    }
                })
        } catch (e: Exception) {
            Toast.makeText(this@FirstPage, "Exception occurred" + e.message, Toast.LENGTH_LONG).show()
        }
    }
}
