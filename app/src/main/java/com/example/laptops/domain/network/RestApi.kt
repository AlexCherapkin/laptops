package com.example.laptops.domain.network

import com.example.laptops.data.model.Product
import retrofit2.http.GET

interface RestApi {
    @GET("apple")
    suspend fun loadApple(): List<Product>
    @GET("asus")
    suspend fun loadAsus(): List<Product>
    @GET("dell")
    suspend fun loadDell(): List<Product>
}