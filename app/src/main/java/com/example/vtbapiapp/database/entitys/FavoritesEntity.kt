package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_department_id")
    val favoriteDepartmentId:Long,
    @ColumnInfo(name = "department_id")
    val departmentId: Long
)