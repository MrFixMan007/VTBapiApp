package com.example.vtbapiapp.database.converters

import androidx.room.TypeConverter
import java.sql.Time

class TimeConverter {
    @TypeConverter
    fun fromTime(time: Time?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun toTime(timeStr: String?): Time? {
        return if (timeStr == null) null else Time.valueOf(timeStr)
    }
}