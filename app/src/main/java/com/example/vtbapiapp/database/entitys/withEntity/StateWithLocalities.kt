package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.StateEntity

data class StateWithLocalities(
    @Embedded
    val state: StateEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "state_id"
    )
    val localities: List<LocalityEntity>
)
