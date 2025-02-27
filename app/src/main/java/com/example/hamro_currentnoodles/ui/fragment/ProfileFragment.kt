package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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

        // Save Button Click Listener
        binding.SaveButton.setOnClickListener {
            saveUserData()
        }

        // Logout Button Click Listener
        binding.logOut.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun fetchUserData() {
        val userId = firebaseAuth.currentUser?.uid

        // Check if the user is logged in
        if (userId != null) {
            // Fetch the data from Firebase Database under the user's ID
            databaseReference.child(userId).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    // Set the values to the UI (password won't be fetched)
                    binding.ProfileEmail.setText(snapshot.child("email").value.toString())
                    binding.ProfileUsername.setText(snapshot.child("username").value.toString())
                    // The password will not be shown in the profile for security reasons
                    binding.ProfilePassword.setText("********")  // Mask the password field
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserData() {
        val email = binding.ProfileEmail.text.toString()
        val username = binding.ProfileUsername.text.toString()
        val password = binding.ProfilePassword.text.toString()

        val userId = firebaseAuth.currentUser?.uid

        // Check if the user is logged in
        if (userId != null) {
            // Update email in Firebase Authentication
            firebaseAuth.currentUser?.updateEmail(email)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // If password is provided and is different, update password as well
                    if (password.isNotEmpty()) {
                        firebaseAuth.currentUser?.updatePassword(password)?.addOnCompleteListener { passwordTask ->
                            if (passwordTask.isSuccessful) {
                                // Update username in Firebase Database (not the password)
                                val userUpdates = mapOf(
                                    "email" to email,
                                    "username" to username
                                )
                                databaseReference.child(userId).updateChildren(userUpdates).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(context, "User data updated", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Failed to update username", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Failed to update password", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        // Update username only if no password change
                        val userUpdates = mapOf(
                            "email" to email,
                            "username" to username
                        )
                        databaseReference.child(userId).updateChildren(userUpdates).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(context, "User data updated", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed to update username", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Failed to update email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun logout() {
        val userId = firebaseAuth.currentUser?.uid

        // Check if the user is logged in
        if (userId != null) {
            // Delete user data from Firebase Realtime Database
            databaseReference.child(userId).removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Now sign out the user
                    firebaseAuth.signOut()
                    Toast.makeText(context, "Logged out and user data deleted", Toast.LENGTH_SHORT).show()

                    // You can navigate to another activity here if needed, like a login screen
                    // For example, you can use an Intent to navigate to the LoginActivity.
                    // Intent(context, LoginActivity::class.java).also { startActivity(it) }
                } else {
                    Toast.makeText(context, "Failed to delete user data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
