package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.WorkDaysEntity

data class DepartmentWithWorkDays(
    @Embedded val department: DepartmentEntity,
    @Relation(
        parentColumn = "work_days_ur_id",
        entityColumn = "id"
    )
    val workDaysUr: WorkDaysEntity,
    @Relation(
        parentColumn = "work_days_fiz_id",
        entityColumn = "id"
    )
    val workDaysFiz: WorkDaysEntity
)
