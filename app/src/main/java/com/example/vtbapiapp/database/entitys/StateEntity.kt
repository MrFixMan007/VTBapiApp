package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state")
data class StateEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    @ColumnInfo(name="country_id")
    val countryId: Long?,
    val name: String?
)