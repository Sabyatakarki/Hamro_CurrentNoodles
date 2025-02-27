package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.hamro_currentnoodles.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        val usernameInput = view.findViewById<TextInputEditText>(R.id.textInputLayout6)
        val emailInput = view.findViewById<TextInputEditText>(R.id.textInputLayout7)
        val phoneInput = view.findViewById<TextInputEditText>(R.id.textInputLayout8)
        val saveButton = view.findViewById<Button>(R.id.SaveButton)

        saveButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()

            if (username.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                saveUserData(username, email, phone)
            }
        }

        return view
    }

    private fun saveUserData(username: String, email: String, phone: String) {
        val userId = auth.currentUser?.uid ?: return
        val userMap = mapOf(
            "username" to username,
            "email" to email,
            "phone" to phone
        )

        database.child(userId).setValue(userMap)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Profile updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT).show()
            }
    }
}
