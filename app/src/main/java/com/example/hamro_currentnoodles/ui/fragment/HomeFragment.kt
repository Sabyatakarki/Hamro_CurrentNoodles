package com.example.hamro_currentnoodles.ui.fragmentNo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.NoodleAdapter
import com.example.hamro_currentnoodles.adapter.VerticalNoodlesAdapter
import com.example.hamro_currentnoodles.model.Noodle

class HomeFragment : Fragment() {
    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var horizontalAdapter: NoodleAdapter
    private lateinit var verticalAdapter: VerticalNoodlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize Horizontal RecyclerView
        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView)
        horizontalRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Horizontal noodles list without description
        val horizontalNoodleList = listOf(
            Noodle("Cheese flavor", R.drawable.pinkcurrent, "NPR 50"),
            Noodle("3x spicy", R.drawable.threex, "NPR 60"),
            Noodle("Spicy Sticks", R.drawable.hotsticks, "NPR 50"),
            Noodle("Hot & Spicy", R.drawable.hot, "NPR 50"),
            Noodle("Lemon flavor", R.drawable.lemom, "NPR 55")
        )
        horizontalAdapter = NoodleAdapter(horizontalNoodleList)
        horizontalRecyclerView.adapter = horizontalAdapter

        // Initialize Vertical RecyclerView
        verticalRecyclerView = view.findViewById(R.id.VerticalRecycleView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Vertical noodles list with description
        val verticalNoodleList = listOf(
            Noodle("Cheese Balls", R.drawable.cheesyballs, "NPR 50", "The irresistible current cheese balls, your delectable and satisfying choice for delightful lite bite snacks."),
            Noodle("Hot and spicy sticks", R.drawable.hotsticks, "NPR 60", "Current Sticks is a yummy treat full of flavor with hot and spicy."),
            Noodle("Potato Cracker", R.drawable.potatocraker, "NPR 55", "Potato cracker with a masala twist made with high-quality ingredients. It’s a delicious bite!"),
            Noodle("Achari Sticks", R.drawable.acharisticks, "NPR 65", "Crispy ‘N’ Crunchy with a delicious taste from the amazing blend of spices."),
            Noodle("Spicy Cheese Balls", R.drawable.spicycheese, "NPR 70", "The current spicy cheese balls spice up your day with a fiery blend of aromatic ingredients.")
        )
        verticalAdapter = VerticalNoodlesAdapter(verticalNoodleList)
        verticalRecyclerView.adapter = verticalAdapter

        return view
    }
}
