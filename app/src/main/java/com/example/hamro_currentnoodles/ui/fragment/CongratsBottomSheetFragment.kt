package com.example.hamro_currentnoodles.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hamro_currentnoodles.databinding.FragmentCongratsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CongratsBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCongratsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCongratsBottomSheetBinding.inflate(inflater, container, false)

        binding.goBack.setOnClickListener {
            // Dismiss the bottom sheet
            dismiss()

            // Navigate to ProductFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ProductsFragment()) // Replace with your container ID if different
                .commit()
        }

        return binding.root
    }
}
