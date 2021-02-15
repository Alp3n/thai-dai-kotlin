package com.example.thaidai.backend.network

import com.example.thaidai.backend.network.responses.ItemOneResponse
import com.example.thaidai.backend.network.responses.ItemListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemService {

    @GET("item")
    suspend fun getItems(
        @Query("type") query: String
    ): ItemListResponse

    @GET("item/{id}")
    suspend fun getOneItem(
        @Path("id") id: String
    ): ItemOneResponse

}