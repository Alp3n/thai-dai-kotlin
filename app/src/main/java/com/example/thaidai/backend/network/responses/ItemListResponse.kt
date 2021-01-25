package com.example.thaidai.backend.network.responses

import com.example.thaidai.backend.network.ItemDto
import com.google.gson.annotations.SerializedName

data class ItemListResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("items")
    var items: List<ItemDto>
)