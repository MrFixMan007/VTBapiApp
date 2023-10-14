package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.LocalityEntity

data class LocalityWithDepartment (
    @Embedded
    val localityEntity: LocalityEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "locality_id"
    )
    val departments: List<DepartmentEntity>
)