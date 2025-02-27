package com.example.hamro_currentnoodles.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.ui.activity.UserLoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()

        val usernameInput = view.findViewById<TextInputEditText>(R.id.textInputLayout6)
        val emailInput = view.findViewById<TextInputEditText>(R.id.textInputLayout7)
        val phoneInput = view.findViewById<TextInputEditText>(R.id.textInputLayout8)
        val saveButton = view.findViewById<Button>(R.id.SaveButton)
        val logOutButton = view.findViewById<Button>(R.id.logOut)

        saveButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                saveUserData(username, email, phone)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        logOutButton.setOnClickListener {
            logoutUser()
        }

        return view
    }

    private fun saveUserData(username: String, email: String, phone: String) {
        val userId = auth.currentUser?.uid ?: return
        val database = FirebaseDatabase.getInstance().getReference("Users")

        val userMap = mapOf("username" to username, "email" to email, "phone" to phone)

        database.child(userId).setValue(userMap)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Profile Saved", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to Save", Toast.LENGTH_SHORT).show()
            }
    }

    private fun logoutUser() {
        val userId = auth.currentUser?.uid ?: return
        val database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(userId).removeValue()
        auth.signOut()

        startActivity(Intent(requireContext(), UserLoginActivity::class.java))
        requireActivity().finish()
    }
}
