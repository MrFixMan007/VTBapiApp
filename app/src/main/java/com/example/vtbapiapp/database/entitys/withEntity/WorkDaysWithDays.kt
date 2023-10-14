package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.DayEntity
import com.example.vtbapiapp.database.entitys.WorkDaysEntity

data class WorkDaysWithDays(
    @Embedded
    val workDays: WorkDaysEntity,
    @Relation(
        parentColumn = "day1_id",
        entityColumn = "id"
    )
    val day1Entity: DayEntity,
    @Relation(
        parentColumn = "day2_id",
        entityColumn = "id"
    )
    val day2Entity: DayEntity,
    @Relation(
        parentColumn = "day3_id",
        entityColumn = "id"
    )
    val day3Entity: DayEntity,
    @Relation(
        parentColumn = "day4_id",
        entityColumn = "id"
    )
    val day4Entity: DayEntity,
    @Relation(
        parentColumn = "day5_id",
        entityColumn = "id"
    )
    val day5Entity: DayEntity,
    @Relation(
        parentColumn = "day6_id",
        entityColumn = "id"
    )
    val day6Entity: DayEntity,
    @Relation(
        parentColumn = "day7_id",
        entityColumn = "id"
    )
    val day7Entity: DayEntity
)
