package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyLocality (
    @PrimaryKey
    val id:Long = 0,
    @ColumnInfo(name = "locality_id")
    val localityId:Long,
)