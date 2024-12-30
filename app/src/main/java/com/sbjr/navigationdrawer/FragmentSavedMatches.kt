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

class FragmentSavedMatches : Fragment() {
    private lateinit var savedMatchDao: SavedMatchDao
    private lateinit var savedAdapter: SavedAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.saved_matches, container, false)
        var txtView : TextView = view.findViewById(R.id.saveView)
        txtView.text = "Saved Matches Fragment"
         recyclerView = view.findViewById(R.id.saved_matches_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        savedMatchDao = MatchDatabase.getDatabase(requireContext()).savedMatchDao()

        fetchSavedMatches()



        return view
    }

    fun fetchSavedMatches(){
        lifecycleScope.launch {
            val savedMatches = withContext(Dispatchers.IO) {
                savedMatchDao.getAllMatches()
            }
            savedAdapter = SavedAdapter(savedMatches, savedMatchDao) { match ->
                deleteMatch(match)
            }
            recyclerView.adapter = savedAdapter
        }
    }

    private fun deleteMatch(match: SavedData) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                savedMatchDao.deleteMatch(match)
            }
            fetchSavedMatches() // Refresh
        }

    }
}