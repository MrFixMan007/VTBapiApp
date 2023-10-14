package com.example.vtbapiapp.database

import androidx.room.TypeConverter
import java.sql.Time

class Converters() {
    @TypeConverter
    fun fromTime(time: Time): Long {
        return time.time
    }

    @TypeConverter
    fun toTime(timestamp: Long): Time {
        return Time(timestamp)
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
