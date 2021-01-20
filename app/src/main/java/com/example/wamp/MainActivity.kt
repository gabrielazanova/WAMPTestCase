package com.example.wamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var  sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        //assign values to each control in the layout
        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)

        var username: String
        var password: String

        loginButton.setOnClickListener {
            username = usernameField.text.toString()
            password = passwordField.text.toString()

            try {
                apiClient.getApiService().login(LoginRequest(email = username, password = password))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Error logging in
                            Toast.makeText(this@MainActivity, "Error occurred!", Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            val loginResponse = response.body()

                            if (response.code() == 200) {
                                //Save the token
                                sessionManager.saveAuthToken(loginResponse!!.authToken)

                                //open Activity FirstPage
                                val intent = Intent(this@MainActivity, FirstPage::class.java)
                                startActivity(intent)
                            } else {
                                // Error logging in
                                Toast.makeText(this@MainActivity, "Incorrect username or password!", Toast.LENGTH_LONG).show()
                            }
                        }
                    })
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Exception occurred" + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
