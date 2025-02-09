package com.example.hamro_currentnoodles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.databinding.CartItemBinding

class CardAdapter (private  val CartItems:MutableList<String>,private val CartiIemPrice:MutableList<String>,private val CartImage:MutableList<Int>)
    : RecyclerView.Adapter<CardAdapter.CartViewHolder>() {
    private val itemQuantities = IntArray(CartItems.size) { 1 }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int = CartItems.size

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartName.text = CartItems[position]
                cartPrice.text = CartiIemPrice[position]
                cartImage.setImageResource(CartImage[position])
                cartItemQuantity.text = quantity.toString()

                minusBtn.setOnClickListener {
                    decreseQuantity(position)

                }
                addBtn.setOnClickListener {
                    increseQuantity(position)

                }
                trashBtn.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition!= RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)
                    }

                }

            }
        }

        private fun increseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }

        }

        private fun decreseQuantity(position: Int) {
            if (itemQuantities[position] > 0) {
                itemQuantities[position]--
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
            CartItems.removeAt(position)
            CartImage.removeAt(position)
            CartiIemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, CartItems.size)

        }

    }
}






