package com.example.wamp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wamp.R

class LoginPage : AppCompatActivity() {

    private val model: LoginPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        //assign values to each control in the layout
        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)

        var username: String
        var password: String

        // Observer of the authentication token.
        val authToken = androidx.lifecycle.Observer<String> {
            //open Activity FirstPage
            val intent = Intent(this@LoginPage, MatchesPage::class.java)
            startActivity(intent)
        }
        // Observer of the authentication error.
        val currentError = androidx.lifecycle.Observer<String> { newError ->
            Toast.makeText(this@LoginPage, newError, Toast.LENGTH_LONG).show()
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentAuthToken.observe(this, authToken)
        model.currentError.observe(this, currentError)

        loginButton.setOnClickListener {
            username = usernameField.text.toString()
            password = passwordField.text.toString()

            if (username != "" && password != "" ) {
                model.userLogin(username, password)
            } else {
                Toast.makeText(this@LoginPage, "Username and password should be filled!", Toast.LENGTH_LONG).show()
            }
        }
    }
}

