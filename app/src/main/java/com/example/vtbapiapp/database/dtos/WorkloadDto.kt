package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class WorkloadDto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("department_id")
    val department_id: Long? = null,
    @SerializedName("people_count")
    val people_count: Int? = null
)