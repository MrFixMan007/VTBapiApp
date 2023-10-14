package com.example.vtbapiapp.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo.*

@Entity
data class WorkloadEntity  (
    @PrimaryKey
    val id:Long,
    val people_count: Int? = null
)