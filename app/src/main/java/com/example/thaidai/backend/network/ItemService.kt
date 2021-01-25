package com.example.thaidai.backend.network

import com.example.thaidai.backend.network.responses.ItemOneResponse
import com.example.thaidai.backend.network.responses.ItemListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemService {

    @GET("items")
    suspend fun getItems(
        //TODO implement auth
//        @Header("Authorization") token: String,
    ): ItemListResponse

    @GET("items/{id}")
    suspend fun getOneItem(
//        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ItemOneResponse

}