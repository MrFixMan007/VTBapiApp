package com.example.vtbapiapp.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
@Entity(tableName = "day")
data class DayEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val start: Time?,
    val finish: Time?
)