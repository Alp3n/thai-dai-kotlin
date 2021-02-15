package com.example.thaidai.backend.domain.item

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    @SerializedName("en")
    val nameEn: String,
    @SerializedName("th")
    val nameTh: String,
    @SerializedName("pron")
    val namePron: String,
) : Parcelable