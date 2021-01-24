package com.example.thaidai.model.network.responses

import com.example.thaidai.model.network.ItemNetwork
import com.google.gson.annotations.SerializedName

class ItemListResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("items")
    var items: List<ItemNetwork>
)