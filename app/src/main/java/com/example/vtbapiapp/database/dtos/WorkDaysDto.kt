package com.example.vtbapiapp.database.dtos

import com.example.vtbapiapp.database.dtos.DayDto
import com.google.gson.annotations.SerializedName
data class WorkDaysDto (
    @SerializedName("id")
    val id: Long,
    @SerializedName("day1")
    val day1: DayDto? = null,
    @SerializedName("day2")
    val day2: DayDto? = null,
    @SerializedName("day3")
    val day3: DayDto? = null,
    @SerializedName("day4")
    val day4: DayDto? = null,
    @SerializedName("day5")
    val day5: DayDto? = null,
    @SerializedName("day6")
    val day6: DayDto? = null,
    @SerializedName("day7")
    val day7: DayDto? = null
)

