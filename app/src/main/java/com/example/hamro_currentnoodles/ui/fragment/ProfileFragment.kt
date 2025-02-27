package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hamro_currentnoodles.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth and Database Reference
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        // Fetch user data from Firebase
        fetchUserData()

        return binding.root
    }

    private fun fetchUserData() {
        val userId = firebaseAuth.currentUser?.uid

        // Check if the user is logged in
        if (userId != null) {
            // Fetch the data from Firebase Database under the user's ID
            databaseReference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Get user data from Firebase and set it to the views
                        binding.ProfileEmail.setText(snapshot.child("email").value.toString())
                        binding.ProfileUsername.setText(snapshot.child("username").value.toString())
                        binding.ProfilePassword.setText(snapshot.child("password").value.toString()) // Assuming you save password (not recommended, see below)
                    } else {
                        Toast.makeText(context, "No data found for this user", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed to load user data", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }
}
