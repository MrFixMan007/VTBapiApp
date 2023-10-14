package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class ATM_Dto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("availabilityDto")
    val availabilityDto: AvailabilityDto? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("coord_x")
    val coord_x: String? = null,
    @SerializedName("coord_y")
    val coord_y: String? = null,
    @SerializedName("allday")
    val allday: Boolean? = null,
    @SerializedName("localityId")
    val localityId: Long? = null
    )