package com.example.thaidai.model.app.item

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meat(
//    val id: String? = null,
    @SerializedName("en")
    val nameEn: String? = null,
    @SerializedName("th")
    val nameTh: String? = null,
    @SerializedName("pron")
    val namePron: String? = null,
) : Parcelable