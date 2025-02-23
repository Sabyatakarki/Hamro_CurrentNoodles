package com.example.hamro_currentnoodles.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityUserSignupBinding
import com.example.hamro_currentnoodles.model.UserModel
import com.example.hamro_currentnoodles.repository.UserRepositoryImpl
import com.example.hamro_currentnoodles.viewmodel.UserViewModel

class UserSignupActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserSignupBinding

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityUserSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        binding.SignUpButton.setOnClickListener {

            var email = binding.Email.text.toString()
            var username = binding.UsernameS.text.toString()
            var password = binding.PassS.text.toString()

            userViewModel.signup(email, password) { success, message, userId ->
                if (success) {
                    var userModel = UserViewModel(
                        userId,
                        username,email
                    )
                    userViewModel.addUserToDatabase(userId, UserModel()) { success, message ->
                        if (success) {
                            Toast.makeText(
                                this@UserSignupActivity,
                                message, Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this@UserSignupActivity,
                                message, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@UserSignupActivity,
                        message, Toast.LENGTH_LONG
                    ).show()
                }
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}
