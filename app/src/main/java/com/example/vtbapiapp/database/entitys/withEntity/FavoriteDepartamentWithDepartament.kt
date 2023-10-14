package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.FavoritesEntity

data class FavoriteDepartmentWithDepartment (
    @Embedded
    val favoritesEntity: FavoritesEntity,
    @Relation(
        parentColumn = "department_id",
        entityColumn = "id"
    )
    val departmentEntity: DepartmentEntity
)