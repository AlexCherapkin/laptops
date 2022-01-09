package com.example.laptops.network

import com.example.laptops.model.Product
import retrofit2.http.GET

interface RestApi {
    @GET("apple")
    suspend fun loadApple(): List<Product>
    @GET("asus")
    suspend fun loadAsus(): List<Product>
    @GET("dell")
    suspend fun loadDell(): List<Product>
}