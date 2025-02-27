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
import com.example.hamro_currentnoodles.repository.UserRepositoryImpl
import com.example.hamro_currentnoodles.viewmodel.UserViewModel

class UserLoginActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var binding: ActivityUserLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            userViewModel.login(username, password) { success, message ->
                if (success) {
                    val selectedId = binding.radioGroup.checkedRadioButtonId

                    val intent = when (selectedId) {
                        R.id.UserRadio -> Intent(this@UserLoginActivity, NavigationActivity::class.java)
                        R.id.AdminRadio-> Intent(this@UserLoginActivity, AdminActivity::class.java)
                        else -> {
                            Toast.makeText(this@UserLoginActivity, "Please select User or Admin", Toast.LENGTH_LONG).show()
                            return@login
                        }
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this@UserLoginActivity, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, UserSignupActivity::class.java)
            startActivity(intent)
        }

        binding.FPass.setOnClickListener {
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
