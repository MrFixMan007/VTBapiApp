package com.example.vtbapiapp.database.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Time
data class DayDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("start")
    val start: Time? = null,
    @SerializedName("finish")
    val finish: Time? = null
)
{
    override fun toString() = "$start - $finish"
}