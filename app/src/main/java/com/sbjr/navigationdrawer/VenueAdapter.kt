package com.sbjr.navigationdrawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VenueAdapter(private val context: List<Venues>) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>(){

    private val dbHelper = VenueDBHelper(context)

    class VenueViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val venueName: TextView = itemView.findViewById(R.id.venue_name)
        val venueAddress: TextView = itemView.findViewById(R.id.venue_address)
        val starIcon: ImageView = itemView.findViewById(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.venue_adapter,parent,false)
        return VenueViewHolder(view)
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        //Abstract there will be no return type
        val venue = venues[position]
        holder.venueName.text = venue.name
        holder.venueAddress.text = venue.location.address
        val isSaved = dbHelper.isMatchSaved(venue.id)
        holder.starIcon.setImageResource(if (isSaved) R.drawable.ic_star_active else R.drawable.ic_star_inactive)


        holder.starIcon.setOnClickListener {
            if (isSaved) {
            } else {
                dbHelper.saveMatch(venue.id, venue.name)
                holder.starIcon.setImageResource(R.drawable.ic_star_active)
            }
        }
    }
}