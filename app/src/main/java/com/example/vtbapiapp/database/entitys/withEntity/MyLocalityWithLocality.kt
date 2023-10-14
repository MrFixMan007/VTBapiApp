package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.MyLocality

data class MyLocalityWithLocality (
    @Embedded
    val myLocality: MyLocality,
    @Relation(
        parentColumn = "locality_id",
        entityColumn = "id"
    )
    val localityEntity: LocalityEntity
)