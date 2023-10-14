package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("stateDtoList")
    val stateDtoList: List<StateDto>? = null,
    @SerializedName("name")
    val name: String? = null

)