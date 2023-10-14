package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PartEntity (
    @PrimaryKey
    val id:Long,
    @ColumnInfo(name = "service_capability")
    val serviceCapability: ServiceTypes,
    @ColumnInfo(name = "service_activity")
    val serviceActivity: ServiceTypes,
    @ColumnInfo(name = "large_bills")
    val largeBills:Boolean,
    @ColumnInfo(name = "small_bills")
    val smallBills:Boolean,

    )