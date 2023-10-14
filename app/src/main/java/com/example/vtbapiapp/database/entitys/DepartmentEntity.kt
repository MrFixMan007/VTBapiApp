package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo.*


@Entity(tableName = "department")
data class DepartmentEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    @ColumnInfo(name = "locality_id")
    val localityId: Long?,
    @ColumnInfo(name = "work_days_ur_id")
    val workDaysUrId: Long?,
    @ColumnInfo(name = "work_days_fiz_id")
    val workDaysFizId: Long?,
    @ColumnInfo(name = "address")
     val address: String,
    @ColumnInfo(name = "coord_x")
     val coord_x: String,
    @ColumnInfo(name = "coord_y")
     val coord_y: String,
    @ColumnInfo(name = "postcode")
    val postcode: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "office_type")
    val office_type: String,
    @ColumnInfo(name = "sale_point_format")
    val sale_point_format: String,
    @ColumnInfo(name = "suo_availability")
    val suo_availability: String,
    @ColumnInfo(name = "has_ramp")
    val has_ramp: Boolean,
    @ColumnInfo(name = "kep")
    val kep: Boolean,
    @ColumnInfo(name = "myBranch")
    val myBranch: Boolean
)