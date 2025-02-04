package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.NoodleAdapter
import com.example.hamro_currentnoodles.model.Noodle

class HomeFragment : Fragment() {
     lateinit var recyclerView: RecyclerView
     lateinit var adapter: NoodleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.horizontalRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val noodleList = listOf(
            Noodle("Cheese flavor", R.drawable.pinkcurrent, "NPR 50"),
            Noodle("2x Spicy", R.drawable.twoxspicy, "NPR 60"),
            Noodle("3x Spicy", R.drawable.threex, "NPR 70"),
            Noodle("Hot & Spicy", R.drawable.hot, "NPR 50"),
            Noodle("Lemon flavor", R.drawable.lemom, "NPR 55")
        )


        // Set Adapter
        adapter = NoodleAdapter(noodleList)
        recyclerView.adapter = adapter

        return view
    }
}
