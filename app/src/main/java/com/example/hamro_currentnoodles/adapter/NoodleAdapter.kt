package com.example.hamro_currentnoodles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.model.Noodle

class NoodleAdapter(private val noodles: List<Noodle>) :
    RecyclerView.Adapter<NoodleAdapter.NoodleViewHolder>() {

    class NoodleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.noodleImage)
        val textView: TextView = view.findViewById(R.id.noodleName)
        val priceView: TextView = view.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoodleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_design, parent, false)
        return NoodleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoodleViewHolder, position: Int) {
        val noodle = noodles[position]
        holder.textView.text = noodle.name
        holder.imageView.setImageResource(noodle.imageResId)
        holder.priceView.text = noodle.price
    }

    override fun getItemCount(): Int = noodles.size
}