package com.example.laptops.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.laptops.data.model.Product

@Database(
    entities = [Product::class],
    version = 1
)

abstract class LaptopsDatabase : RoomDatabase() {
    abstract fun laptopsDao(): LaptopsDao
}