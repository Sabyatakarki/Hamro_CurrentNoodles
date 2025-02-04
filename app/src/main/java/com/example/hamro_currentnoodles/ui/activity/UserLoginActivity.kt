package com.example.hamro_currentnoodles.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityUserLoginBinding
import com.example.hamro_currentnoodles.viewmodel.UserViewModel

class UserLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener{
            val intent = Intent(
                this,UserSignupActivity::class.java)
            startActivity(intent)
        }

        var email = binding.username.text.toString()
        var password = binding.password.text.toString()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}