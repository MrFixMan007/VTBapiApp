package com.example.vtbapiapp.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    val name: String?
)