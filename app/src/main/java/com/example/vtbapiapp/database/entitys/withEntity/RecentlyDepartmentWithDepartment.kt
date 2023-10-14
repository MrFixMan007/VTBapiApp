package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.RecentlyDepartment

data class RecentlyDepartmentWithDepartment(
    @Embedded
    val recentlyDepartment: RecentlyDepartment,
    @Relation(
        parentColumn = "department_id",
        entityColumn = "id"
    )
    val department: DepartmentEntity
)