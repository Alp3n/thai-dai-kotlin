package com.example.thaidai.backend.network.responses

import com.example.thaidai.backend.network.ItemDto
import com.google.gson.annotations.SerializedName

data class ItemOneResponse(
    @SerializedName("item")
    var item: ItemDto
)