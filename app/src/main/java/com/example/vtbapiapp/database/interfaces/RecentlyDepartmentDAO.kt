package com.example.vtbapiapp.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vtbapiapp.database.entitys.RecentlyDepartment
import com.example.vtbapiapp.database.entitys.withEntity.RecentlyDepartmentWithDepartment

@Dao
interface RecentlyDepartmentDAO {
    @Insert
    suspend fun insert(recentlyDepartment: RecentlyDepartment)

    @Query("DELETE FROM recently_departments WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Delete
    suspend fun deleteRecently(recentlyDepartment: RecentlyDepartment)
    @Query("DELETE FROM recently_departments WHERE id = :departmentId")
    suspend fun deleteByDepartmentId(departmentId: Long)

    @Query("SELECT COUNT(*) FROM recently_departments")
    suspend fun getRecentlyDepartmentsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentlyDepartment(recentlyDepartment: RecentlyDepartment)
    @Transaction
    @Query("SELECT * FROM recently_departments")
    suspend fun getAllRecentlyDepartments(): List<RecentlyDepartmentWithDepartment>

}