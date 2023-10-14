package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class PartDto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("service_capability")
    val service_capability: Boolean? = null,
    @SerializedName("service_activity")
    val service_activity: Boolean? = null,
    @SerializedName("large_bills")
    val large_bills: Boolean? = null,
    @SerializedName("small_bills")
    val small_bills: Boolean? = null
)