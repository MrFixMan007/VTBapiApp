package com.example.vtbapiapp.database.dtos

import com.example.vtbapiapp.database.dtos.DepartmentDto
import com.google.gson.annotations.SerializedName

data class LocalityDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("departmentDtoList")
    val departmentDtoList: List<DepartmentDto>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("stateId")
    val stateId:Long
)