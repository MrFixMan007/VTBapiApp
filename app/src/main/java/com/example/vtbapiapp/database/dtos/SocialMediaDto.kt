package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class SocialMediaDto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)