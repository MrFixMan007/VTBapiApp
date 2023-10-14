package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class AtmEntity (
    @PrimaryKey
    val id:Long,
    @ColumnInfo(name= "locality_id")
    val localityId:Long,
    @ColumnInfo(name= "availability_entity_id")
    val availabilityEntityId:Long,
    val address:String,
    val coord_x:String,
    val coord_y:String,
    @ColumnInfo(name ="all_day")
    val allDay:Boolean,
    val description:String?
)