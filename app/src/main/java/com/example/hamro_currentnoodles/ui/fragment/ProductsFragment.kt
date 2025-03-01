package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.CardAdapter
import com.example.hamro_currentnoodles.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {
    private lateinit var binding : FragmentProductsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)



        val cartFoodName = listOf( "ChessySpicyFlavo","3xChickenFlavor","hot Flavor","Lemon flavor","2x spicy","ChesseBalls")
        val cartItemPrice = listOf( "NPR 55","NPR 50","NPR 50","NPR 55","NPR 50","NPR 50")
        val cartImage = listOf(
            R.drawable.pinkcurrent,
            R.drawable.threex,
            R.drawable.hot,
            R.drawable.lemom,
            R.drawable.twoxspicy,
            R.drawable.cheesyballs
        )
        val adapter = CardAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.ProductRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.ProductRecycle.adapter = adapter
        return binding.root
        return inflater.inflate(R.layout.fragment_products, container, false)
    }
}

