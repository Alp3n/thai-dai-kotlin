package com.example.thaidai.model.network

import com.example.thaidai.model.app.item.*
import com.google.gson.annotations.SerializedName

class ItemNetwork(

    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("names")
    val names: Name? = null,
    @SerializedName("desc")
    val description: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("meats")
    val meats: List<Meat>? = null,
    @SerializedName("allergens")
    val allergens: List<String>? = null,

    //TODO change model in API
//    @SerializedName("attributes")
//    val attributes: List<Attribute>? = null,

    @SerializedName("ingredients")
    val ingredients: List<String>? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("spicy")
    val spicy: Boolean? = null,
    @SerializedName("egg")
    val egg: Boolean? = null,
    @SerializedName("sweet")
    val sweet: Boolean? = null,
)