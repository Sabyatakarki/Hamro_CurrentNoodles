package com.example.hamro_currentnoodles.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityUserSignupBinding

class UserSignupActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserSignupBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle Sign Up Button Click
        binding.SignUpButton.setOnClickListener {
            val email = binding.textInputLayout3.editText?.text.toString().trim()
            val username = binding.textInputLayout4.editText?.text.toString().trim()
            val password = binding.textInputLayout5.editText?.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
                finish()  // Close this activity and go back to login
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
