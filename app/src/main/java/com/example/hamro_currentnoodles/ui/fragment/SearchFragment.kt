package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.MenuAdapter
import com.example.hamro_currentnoodles.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private lateinit var  adapter : MenuAdapter
    private val originalmenuitemName = listOf("Chessy flavor ", "3x spipcy", "cheese balls", "Potato Cracker", "2x spicy", "Chicken Noodles", "Achari sticks",
        "Hot Sticks", "Hot and spicy", "Spicy ChesseBalls")
    private val originalmenufoodprice = listOf("Rs 650", "Rs 750", "Rs 1050", "Rs 750", "Rs 950", "Rs 750", "Rs 850", "Rs 850", "Rs 950", "Rs 950")
    private val originalmenuImage = listOf(
        R.drawable.pinkcurrent,
        R.drawable.threex,
        R.drawable.cheesyballs,
        R.drawable.potatocraker,
        R.drawable.twoxspicy,
        R.drawable.chicken,
        R.drawable.acharisticks,
        R.drawable.hotsticks,
        R.drawable.hot,
        R.drawable.spicycheese,
    )


    private val filteredmenuitemName = mutableListOf<String>()
    private val filteredmenufoodprice = mutableListOf<String>()
    private val filteredmenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchBinding.inflate(inflater,container,false)
        adapter = MenuAdapter(filteredmenuitemName,filteredmenufoodprice,filteredmenuImage)
        binding.menuRecylerView.layoutManager=LinearLayoutManager(requireContext())
        binding.menuRecylerView.adapter=adapter

        // set up for search view

        setupSearchView()
        // show all menu items
        showAllMenu()

        return binding.root
    }

    private fun showAllMenu() {
        filteredmenuitemName.clear()
        filteredmenufoodprice.clear()
        filteredmenuImage.clear()


        filteredmenuitemName.addAll(originalmenuitemName)
        filteredmenufoodprice.addAll(originalmenufoodprice)
        filteredmenuImage.addAll(originalmenuImage)

        adapter.notifyDataSetChanged()


    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true

            }
        })

    }

    private fun filterMenuItems(query: String) {
        filteredmenuitemName.clear()
        filteredmenufoodprice.clear()
        filteredmenuImage.clear()

        originalmenuitemName.forEachIndexed { index, itemName ->
            if (itemName.contains(query.toString(),ignoreCase=true)){
                filteredmenuitemName.add(itemName)
                filteredmenufoodprice.add(originalmenufoodprice[index])
                filteredmenuImage.add(originalmenuImage[index])

            }
        }
        adapter.notifyDataSetChanged()
    }


}