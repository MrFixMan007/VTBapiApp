package com.example.vtbapiapp.database

import androidx.room.TypeConverter
import java.sql.Time

class Converters() {
    @TypeConverter
    fun fromTime(time: Time): String {
        return time.time.toString()
    }

    @TypeConverter
    fun toTime(timestamp: String): Time {
        return Time.valueOf(timestamp)
    }

//    @TypeConverter
//    fun toTime(timeStr: String?): Time? {
//        return if (timeStr == null) null else Time.valueOf(timeStr)
//    }
//
//    @TypeConverter
//    fun DayDto.fromDayDto(): DayEntity? {
//        return DayEntity(id, start, finish)
//    }
}
