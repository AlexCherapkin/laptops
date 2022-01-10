package com.example.laptops.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: LaptopsDatabase? = null
    fun provideDatabase(context: Context): LaptopsDatabase {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            LaptopsDatabase::class.java, "databaseLaptops"
        ).build().also { db = it }
    }
}