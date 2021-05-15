package com.example.wamp.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wamp.model.Match
import com.example.wamp.R
import com.example.wamp.api.SessionManager
import com.example.wamp.adapters.MatchesAdapter
import kotlinx.android.synthetic.main.activity_matches_page.*

class MatchesPage : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: MatchesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private val model: MatchesPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_page)

        sessionManager = SessionManager(this@MatchesPage)
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        // Observer of the authentication token.
        val matches = androidx.lifecycle.Observer<MutableList<Match>> { newMatches ->
            adapter = MatchesAdapter( baseContext, newMatches as MutableList<Match>)
            adapter.notifyDataSetChanged()
            recycler_view.adapter = adapter
        }
        // Observer of the authentication error.
        val currentError = androidx.lifecycle.Observer<String> { newError ->
            Toast.makeText(this@MatchesPage, newError, Toast.LENGTH_LONG).show()
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentMatches.observe(this, matches)
        model.currentError.observe(this, currentError)

        model.getMatches(sessionManager.fetchAuthToken().toString())
    }
}
