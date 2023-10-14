package com.example.vtbapiapp.database

import androidx.room.TypeConverter
import com.example.vtbapiapp.database.dtos.DayDto
import com.example.vtbapiapp.database.entitys.DayEntity
import java.sql.Time

class Converters() {
    @TypeConverter
    fun fromTime(time: Time?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun toTime(timeStr: String?): Time? {
        return if (timeStr == null) null else Time.valueOf(timeStr)
    }

    @TypeConverter
    fun DayDto.fromDayDto(): DayEntity? {
        return DayEntity(id, start, finish)
    }
}
