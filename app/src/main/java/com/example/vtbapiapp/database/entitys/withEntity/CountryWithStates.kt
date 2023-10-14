package com.example.vtbapiapp.database.entitys.withEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vtbapiapp.database.entitys.CountryEntity
import com.example.vtbapiapp.database.entitys.StateEntity

data class CountryWithStates(
    @Embedded val country: CountryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "country_id"
    )
    val states: List<StateEntity>
)
