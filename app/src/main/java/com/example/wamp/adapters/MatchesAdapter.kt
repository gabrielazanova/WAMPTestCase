package com.example.wamp.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.wamp.model.Match
import com.example.wamp.R
import kotlinx.android.synthetic.main.match_layout.view.*

class MatchesAdapter( private val context: Context, private val matches: MutableList<Match>): RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val matchView = LayoutInflater.from(context).inflate(R.layout.match_layout, parent, false)
        return MatchesViewHolder(
            matchView
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.txt_id.text = matches[position].id.toString()
        holder.txt_playedAt.text = matches[position].played_at
        holder.txt_likes.text = matches[position].like_count.toString()
        holder.txt_comments.text = matches[position].comment_count.toString()
    }

    class MatchesViewHolder(matchView: View) : RecyclerView.ViewHolder(matchView){
        var txt_id : TextView
        var txt_playedAt : TextView
        var txt_likes : TextView
        var txt_comments : TextView

        init {
            txt_id = matchView.txt_id
            txt_playedAt = matchView.txt_playedAt
            txt_likes = matchView.txt_likes
            txt_comments = matchView.txt_comments
        }
    }
}
