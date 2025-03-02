package com.example.hamro_currentnoodles.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hamro_currentnoodles.databinding.ActivityPayoutBinding
import com.example.hamro_currentnoodles.ui.fragment.ProductsFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayoutBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityPayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Database Reference
        database = FirebaseDatabase.getInstance().getReference("Orders")

        // Set click listener for placing an order
        binding.Placemyorder.setOnClickListener {
            saveOrderToFirebase()
        }
    }

    private fun saveOrderToFirebase() {
        val name = binding.nameEditText.text.toString().trim()
        val address = binding.EditAddress.text.toString().trim()
        val contact = binding.EditPhoneNumber.text.toString().trim()

        if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Generate a unique order ID
        val orderId = database.push().key!!

        // Create an order object (excluding Total Amount)
        val order = Order(orderId, name, address, contact)

        // Save to Firebase
        database.child(orderId).setValue(order).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
                binding.Placemyorder.setOnClickListener{
                    val intent = Intent(this,ProductsFragment::class.java)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this, "Failed to place order", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Data class for Order (Excluding Total Amount)
    data class Order(
        val orderId: String,
        val name: String,
        val address: String,
        val contact: String
    )
}
