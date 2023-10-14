package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class DepartmentDto (
    @SerializedName("id")
    val id: Long,
    @SerializedName("workDaysFizDto")
    val workDaysFizDto: WorkDaysDto? = null,
    @SerializedName("workDaysUrDto")
    val workDaysUrDto: WorkDaysDto? = null,
    @SerializedName("coord_x")
    val coord_x: String? = null,
    @SerializedName("coord_y")
    val coord_y: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("SerializedName")
    val postcode: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("office_type")
    val office_type: String? = null,
    @SerializedName("sale_point_format")
    val sale_point_format: String? = null,
    @SerializedName("suo_availability")
    val suo_availability: String? = null,
    @SerializedName("has_ramp")
    val has_ramp: Boolean? = null,
    @SerializedName("kep")
    val kep: Boolean? = null,
    @SerializedName("myBranch")
    val myBranch: Boolean? = null,
    @SerializedName("localityId")
    val localityId: Long? = null
)
{

}