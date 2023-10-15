package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.withEntity.DepartmentWithWorkDays

@Dao
interface DepartmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertDepartments(vararg departments: DepartmentEntity)
    @Delete
     suspend fun deleteDepartments(vararg departments: DepartmentEntity)
    @Update
     suspend fun updateDepartments(vararg departments: DepartmentEntity)

    @Transaction
    @Query("SELECT * FROM department")
     suspend fun getDepartmentAndWorkDays():List<DepartmentWithWorkDays>

    @Transaction
    @Query("SELECT * FROM department where id = :id")
     suspend fun getDepartmentAndWorkDaysById(id:Long?): DepartmentWithWorkDays
}