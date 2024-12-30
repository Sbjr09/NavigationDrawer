package com.sbjr.navigationdrawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SavedAdapter (private var savedMatches: List<SavedData>,
                    private val savedMatchDao: SavedMatchDao,
                    private val onDeleteClick: (SavedData) -> Unit

) : RecyclerView.Adapter<SavedAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.saveView)
        val starImageView: ImageView = view.findViewById(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_matches, parent, false)
        return SavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val savedMatch = savedMatches[position]
        holder.nameTextView.text = savedMatch.name
        holder.starImageView.setImageResource(R.drawable.ic_star_filled)



        holder.starImageView.setOnClickListener {
            onDeleteClick(savedMatch)
        }

    }
    fun updateMatches(newMatches: List<SavedData>) {
        savedMatches = newMatches
        notifyDataSetChanged()
    }

    override fun getItemCount() = savedMatches.size
}