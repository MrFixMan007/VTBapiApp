package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "recently_departments")
data class RecentlyDepartment(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "department_id") val departmentId: Long,
    @ColumnInfo(name = "time") val time: Time
)