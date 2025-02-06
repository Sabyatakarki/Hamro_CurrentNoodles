package com.example.hamro_currentnoodles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.databinding.CartItemBinding

class CardAdapter (private  val cartItems:MutableList<String>,private val CartiIemPrice:MutableList<String>,private val CartImage:MutableList<Int>)
    : RecyclerView.Adapter<CardAdapter.CartViewHolder>() {
        private val itemQuantities = IntArray(cartItems.size){1}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }



    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int  = cartItems.size

    inner class CartViewHolder (private val binding:CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartName.text = cartItems[position]
                cartPrice.text = CartiIemPrice[position]
                cartImage.setImageResource(CartImage[position])
                cartItemQuantity.text = quantity.toString()
            }



        }

    }
}

