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
    val mon_s: Time,
    val mon_f: Time,
    val tue_s: Time,
    val tue_f: Time,
    val wed_s: Time,
    val wed_f: Time,
    val thu_s: Time,
    val thu_f: Time,
    val fri_s: Time,
    val fri_f: Time,
    val sat_s: Time,
    val sat_f: Time,
    val sun_s: Time,
    val sun_f: Time
)

