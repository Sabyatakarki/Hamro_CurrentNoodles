package com.example.hamro_currentnoodles.model

data class Noodle(
    val name: String,
    val imageResId: Int,
    val price: String? = null, // Make price nullable, so it's optional for vertical noodles
    val caption: String? = null // Add caption field for vertical noodles
)





