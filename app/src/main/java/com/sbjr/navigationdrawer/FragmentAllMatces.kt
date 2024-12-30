package com.sbjr.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAllMatces : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var venueAdapter: VenueAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.all_matches,container,false)
        val txtView : TextView = view.findViewById(R.id.AMtxtView)
        txtView.text= "ALL Matches "

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fethVenues()
    }
    fun fethVenues(){
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getIn.api.getVenues()
                }
                venueAdapter = VenueAdapter(response.response.venues)
                recyclerView.adapter = venueAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        }
    }
