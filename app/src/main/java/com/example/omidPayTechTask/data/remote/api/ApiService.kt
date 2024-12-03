package com.example.omidPayTechTask.data.remote.api

import com.example.omidPayTechTask.data.remote.model.ItemDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getItems(): Response<List<ItemDTO>>

}