package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.WorkDaysEntity
@Dao
interface WorkDaysDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertWorkDays(vararg days: WorkDaysEntity)
    @Delete
     suspend fun deleteWorkDays(vararg days: WorkDaysEntity)
    @Update
     suspend fun updateWorkDays(vararg days: WorkDaysEntity)
}