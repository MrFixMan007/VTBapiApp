package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "locality")
data class LocalityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    @ColumnInfo(name="state_id")
    val stateId: Long? ,
    val name: String?,
)