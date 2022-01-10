package com.example.laptops.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["name","description","image","price"])
data class Product(
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val image: String,
    @ColumnInfo val price: Int
)
