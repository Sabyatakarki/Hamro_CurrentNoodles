package com.example.hamro_currentnoodles.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.databinding.PendingOrderItemsBinding

class PendingOrderAdapter (
    private val customerName : MutableList<String>,
    private val quanntity : MutableList<String>,
    private val foodImage : MutableList<Int>,
    private val context: Context): RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding=PendingOrderItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PendingOrderViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =customerName.size

    inner class PendingOrderViewHolder(private val binding: PendingOrderItemsBinding):RecyclerView.ViewHolder(binding.root){

        private var isAccepted=false

        fun bind(position: Int) {
            binding.apply {
                CustomerName.text=customerName[position]
                Quantity.text=quanntity[position]
                OrderFoodimage.setImageResource(foodImage [position])

                orderAcceptBtn.apply{
                    if(!isAccepted){
                        text = "Accept"
                    }else{
                        text="Dispatch"
                    }
                    setOnClickListener {
                        if(!isAccepted) {
                            text = "Dispatch"
                            isAccepted= true
                            showToast("Order is accepted")

                        }else{
                            customerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order is Dispatched")
                        }
                    }
                }

            }

        }
        private fun showToast(message: String){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }

    }
}