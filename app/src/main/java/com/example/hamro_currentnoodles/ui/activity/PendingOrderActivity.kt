package com.example.hamro_currentnoodles.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.adapter.PendingOrderAdapter
import com.example.hamro_currentnoodles.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPendingOrderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        val orderedcustomerName = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Mike Johnson"
        )

        val orderedQuantity = arrayListOf(
            "9",
            "7",
            "6"
        )

        val orderedFoodImage = arrayListOf(R.drawable.hot,R.drawable.cheesyballs,R.drawable.cheesychicken)

        val adapter = PendingOrderAdapter(orderedcustomerName, orderedQuantity,orderedFoodImage, this)
        binding.pendingorderRecylerView.adapter = adapter
        binding.pendingorderRecylerView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}