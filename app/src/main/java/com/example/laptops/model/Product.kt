package com.example.laptops.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val price: Int,
)
