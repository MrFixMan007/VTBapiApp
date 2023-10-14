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
    val mon_s: Time? = null,
    val mon_f: Time? = null,
    val tue_s: Time? = null,
    val tue_f: Time? = null,
    val wed_s: Time? = null,
    val wed_f: Time? = null,
    val thu_s: Time? = null,
    val thu_f: Time? = null,
    val fri_s: Time? = null,
    val fri_f: Time? = null,
    val sat_s: Time? = null,
    val sat_f: Time? = null,
    val sun_s: Time? = null,
    val sun_f: Time? = null
)

