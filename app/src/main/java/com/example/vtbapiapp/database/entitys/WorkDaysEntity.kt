package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "work_days")
data class WorkDaysEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val workDaysId: Long,  // Переименовано поле для избежания конфликта имен
    val mon_s: String,
    val mon_f: String,
    val tue_s: String,
    val tue_f: String,
    val wed_s: String,
    val wed_f: String,
    val thu_s: String,
    val thu_f: String,
    val fri_s: String,
    val fri_f: String,
    val sat_s: String,
    val sat_f: String,
    val sun_s: String,
    val sun_f: String
)

