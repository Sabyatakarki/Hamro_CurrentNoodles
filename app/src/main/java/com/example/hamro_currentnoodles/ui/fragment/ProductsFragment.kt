package com.example.hamro_currentnoodles.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.CardAdapter
import com.example.hamro_currentnoodles.databinding.FragmentProductsBinding
import com.example.hamro_currentnoodles.ui.activity.PayoutActivity

class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        val cartFoodName = mutableListOf(
            "ChessySpicyFlavo",
            "3xChickenFlavor",
            "hot Flavor",
            "Lemon flavor",
            "2x spicy",
            "ChesseBalls"
        )
        val cartItemPrice =
            mutableListOf("NPR 55", "NPR 50", "NPR 50", "NPR 55", "NPR 50", "NPR 50")
        val cartImage = mutableListOf(
            R.drawable.pinkcurrent,
            R.drawable.threex,
            R.drawable.hot,
            R.drawable.lemom,
            R.drawable.twoxspicy,
            R.drawable.cheesyballs
        )

        val adapter = CardAdapter(cartFoodName, cartItemPrice, cartImage)

        binding.ProductRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.ProductRecycle.adapter = adapter

        // Handle Purchase Button Click (Navigates to PayoutActivity)
        binding.PurchaseBtn.setOnClickListener {
            val intent = Intent(requireContext(), PayoutActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}
