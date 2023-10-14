package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.DayEntity
@Dao
interface DayDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertDays(vararg days: DayEntity)
    @Delete
     suspend fun deleteDays(vararg days: DayEntity)
    @Update
     suspend fun updateDays(vararg days: DayEntity)

}