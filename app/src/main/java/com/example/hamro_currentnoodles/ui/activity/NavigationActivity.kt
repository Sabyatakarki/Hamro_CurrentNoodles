package com.example.hamro_currentnoodles.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hamro_currentnoodles.R
import com.example.hamro_currentnoodles.databinding.ActivityNavigationBinding
import com.example.hamro_currentnoodles.ui.fragment.HomeFragment
import com.example.hamro_currentnoodles.ui.fragment.ProductsFragment
import com.example.hamro_currentnoodles.ui.fragment.ProfileFragment
import com.example.hamro_currentnoodles.ui.fragment.SearchFragment

class NavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        replaceFragment(HomeFragment())

        binding.btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navHome-> replaceFragment(HomeFragment())
                R.id.navSearch ->replaceFragment(SearchFragment())
                R.id.navProducts->replaceFragment(ProductsFragment())
                R.id.navProfile ->replaceFragment(ProfileFragment())
                else -> {}
            }

            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager =
            supportFragmentManager
        val fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            binding.frameLayout.id,
            fragment
        )
        fragmentTransaction.commit()
    }
}

