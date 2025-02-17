package com.example.hamro_currentnoodles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.model.Noodle


class VerticalNoodlesAdapter(private val noodleList: List<Noodle>) : RecyclerView.Adapter<VerticalNoodlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_recycleview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noodle = noodleList[position]
        holder.nameTextView.text = noodle.name
        holder.descriptionTextView.text = noodle.caption  // Bind the description text
        holder.imageView.setImageResource(noodle.imageResId)
    }

    override fun getItemCount(): Int {
        return noodleList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.Snacks)
        val descriptionTextView: TextView = itemView.findViewById(R.id.caption) // New TextView for description
        val imageView: ImageView = itemView.findViewById(R.id.chesseballs)
    }
}


