package com.example.hamro_currentnoodles.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityPayoutBinding

class PayoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.Placemyorder.setOnClickListener{
            val bottomSheetDialog=CongratsBottomSheetFragment()
            bottomSheetDialog.show(supportFragmentManager,"Test")

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}