package com.example.vtbapiapp.database.dtos

import com.example.vtbapiapp.database.dtos.LocalityDto
import com.google.gson.annotations.SerializedName

data class StateDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("localityDtoList")
    val localityDtoList: List<LocalityDto>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("countryId")
    val countryId:Long
)