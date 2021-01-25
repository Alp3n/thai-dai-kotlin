package com.example.thaidai.backend.domain.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String? = null,
    val names: Name? = null,
    val description: String? = null,
    val type: String? = null,
    val meats: List<Meat>? = null,
    val allergens: List<String>? = null,
    val attributes: List<String>? = null,
    val ingredients: List<String>? = null,
    val image: String? = null,
    val spicy: Boolean? = null,
    val egg: Boolean? = null,
    val sweet: Boolean? = null,
) : Parcelable