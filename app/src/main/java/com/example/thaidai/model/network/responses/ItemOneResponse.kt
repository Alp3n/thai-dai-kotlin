package com.example.thaidai.model.network.responses

import com.example.thaidai.model.network.ItemNetwork
import com.google.gson.annotations.SerializedName

class ItemOneResponse(
    @SerializedName("item")
    var item: ItemNetwork
)