package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class MarketingDto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("url")
    val url: String? = null
)